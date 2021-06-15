package cn.edu.hziee.tpp.service;

import cn.edu.hziee.tpp.mapper.*;
import cn.edu.hziee.tpp.model.*;
import cn.edu.hziee.tpp.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SuperService {
    @Autowired
    private CinemaInfoMapper cinemaInfoMapper;
    @Autowired
    private FilmInfoMapper filmInfoMapper;
    @Autowired
    private FilmTimesMapper filmTimesMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private HallInfoMapper hallInfoMapper;
    @Autowired
    private OrderItemInfoMapper orderItemMapper;
    @Autowired
    private UserMapper userMapper;


    public List<String> getDate() {
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
        Date date=new Date();
        List<String> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = Calendar.getInstance();
        calendar2.setTime(date);
        calendar3.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        date = calendar.getTime();
        dates.add(sdf.format(date));
        calendar2.add(Calendar.DAY_OF_MONTH, +2);
        dates.add(sdf.format(calendar2.getTime()));
        calendar3.add(Calendar.DAY_OF_MONTH, +3);
        dates.add(sdf.format(calendar3.getTime()));
        System.out.println(dates);
        return dates;
    }
    public void editCinema(Integer cinemaId,Float grade,String area,String address,String name) {
        CinemaInfo cinema = cinemaInfoMapper.selectByPrimaryKey(cinemaId);
        cinema.setArea(area);
        cinema.setCinemaAddress(address);
        cinema.setCinemaName(name);
        cinema.setGrade(grade);
        System.out.println(cinema);
        cinemaInfoMapper.updateByPrimaryKey(cinema);
    }

    public void delCinema(Integer cinemaId) {
        List<HallInfo> halls = hallInfoMapper.selectByCinema(cinemaId);
        if (halls.size() != 0) {
            System.out.println("删除影院失败");
            return;
        }
        System.out.println("删除影院成功");
        cinemaInfoMapper.deleteByPrimaryKey(cinemaId);
    }

    public List<User> getUserList() {
        return userMapper.selectAll();
    }

    public User getUser(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public void addUser(User user) {
        int i = userMapper.insert(user);
        System.out.println(i);
    }

    public void editUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    public void delUser(Integer userId) {
        List<OrderInfo> orderInfos = orderInfoMapper.selectByUserId(userId);

        if (orderInfos.size() == 0) {
            userMapper.deleteByPrimaryKey(userId);
        }
    }

    public List<HallInfo> getHallsByCinema(Integer cinemaId) {
        return hallInfoMapper.selectByCinema(cinemaId);
    }

    public List<HallInfo> getFreeHalls(Integer cinemaId, String filmDate, Integer time) {
        List<FilmTimes> filmTimes = filmTimesMapper.queryTimes(null, cinemaId, filmDate);
        List<HallInfo> halls = hallInfoMapper.selectByCinema(cinemaId);
        for (FilmTimes filmTime :
                filmTimes) {
            String[] time1 = filmTime.getFilmTime().split("");
            if (Integer.valueOf(time1[0]) < time + 2 && Integer.valueOf(time1[0]) >= time) {
                HallInfo hallInfo = filmTime.getHallInfo();
                for (int i = 0; i < halls.size(); i++) {
                    if (hallInfo.getHallId() == halls.get(i).getHallId()) {
                        halls.remove(i);
                        i--;
                    }
                }
            }
        }
        return halls;
    }
    public void addHall(Integer row, Integer col,Integer cinemaId, String hallName, String[][] checks) {
        String str="";
        for (int i = 0; i < row-1; i++) {
            for (int j = 0; j < col-1; j++) {
                for (int k = 0; k < checks.length; k++) {
                    if (i == Integer.valueOf(checks[k][0]) && j == Integer.valueOf(checks[k][1])) {
                        str += "_";
                    } else {
                        str += "a";
                    }
                }
            }
            str += ",";
        }
        HallInfo hall = new HallInfo();
        hall.setHallName(hallName);
        hall.setSeatInfo(str);
        hall.setCinemaId(cinemaId);

        hallInfoMapper.insert(hall);
        System.out.println("添加影厅成功");
    }

    public void delHall(Integer hallId) {
        List<FilmTimes> times = filmTimesMapper.selectAll();
        for (FilmTimes time :
                times) {
            if (time.getHallId() == hallId) {
                System.out.println("删除影厅失败");
                return;
            }
        }
        System.out.println("删除影厅");
        hallInfoMapper.deleteByPrimaryKey(hallId);
    }

    public void addTimes(Integer hallId, Integer filmId, String date, Integer time,Float price) {
        FilmTimes times = new FilmTimes();
        times.setFilmId(filmId);
        times.setHallId(hallId);
        times.setFilmDate(date);
        times.setFilmPrice(price);
        Integer time2 = time + 2;
        String time_str = time + "：00-" + time2 + "：00";
        times.setFilmTime(time_str);

        filmTimesMapper.insert(times);
    }

    public void delTime(Integer timeId) {
        List<OrderInfo> orders = orderInfoMapper.selectAll();
        for (OrderInfo order:
             orders) {
            if (order.getFilmTimesId() == timeId) {
                System.out.println("删除档期失败");
                return;
            }
        }
        System.out.println("删除档期成功");
        filmTimesMapper.deleteByPrimaryKey(timeId);
    }

    public List<FilmTimes> queryTimes(Integer filmId,Integer cinemaId,String filmDate) {
        return filmTimesMapper.queryTimes(filmId, cinemaId, filmDate);
    }

    public List<CinemaInfo> getCinemaList() {
        return cinemaInfoMapper.selectAll();
    }

    public CinemaInfo getCinema(Integer cinemaId) {
        return cinemaInfoMapper.selectByPrimaryKey(cinemaId);
    }

    public List<OrderInfo> getAllOrder() {
        return orderInfoMapper.selectAll();
    }

    public void offOrder(Integer orderId) {
        OrderInfo order = orderInfoMapper.selectByPrimaryKey(orderId);
        order.setOrderState(2);
        orderInfoMapper.updateByPrimaryKey(order);
    }
    public void sendDing(String phone) {
        String host = "https://jmsms.market.alicloudapi.com";
        String path = "/sms/send";
        String method = "POST";
        String appcode = "f80676de8ea64d008f8565e4080a0eec";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile",phone);
        querys.put("templateId", "M72CB42894");
        querys.put("value", "您有订单未支付，请及时支付哦");
        Map<String, String> bodys = new HashMap<String, String>();

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
