package cn.edu.hziee.tpp.controller;

import cn.edu.hziee.tpp.model.*;
import cn.edu.hziee.tpp.service.FilmService;
import cn.edu.hziee.tpp.service.OrderService;
import cn.edu.hziee.tpp.service.SuperService;
import cn.edu.hziee.tpp.service.UserService;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private SuperService superService;
    @Autowired
    private FilmService filmService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/admin/index")
    public String index(Model model) {
        List<CinemaInfo> cinemas = superService.getCinemaList();
        model.addAttribute("cinemas", cinemas);
        return "views/admin/index";
    }
    @RequestMapping("/admin/cinema/del")
    public String cinemaDel(@RequestParam("cinema_id") Integer cinemaId) {
        superService.delCinema(cinemaId);
        return "redirect:/admin/index";
    }
    @RequestMapping("/admin/cinema/to_edit")
    public String cinema2Edit(@RequestParam("cinema_id") Integer cinemaId
            , Model model) {
        CinemaInfo cinema = superService.getCinema(cinemaId);
        model.addAttribute("cinema", cinema);
        return "views/admin/cinema_edit";
    }
    @RequestMapping("/admin/cinema/edit")
    public String cinemaEdit(@RequestParam("cinema_id") Integer cinemaId,
                             @RequestParam("grade") Float grade,
                             @RequestParam("area") String area,
                             @RequestParam("address") String address,
                             @RequestParam("name") String name,
                             Model model) {
        System.out.println(name);
        superService.editCinema(cinemaId, grade, area, address, name);
        return "redirect:/admin/index";
    }


    @RequestMapping("/admin/user")
    public String userManager(Model model) {
        List<User> users = superService.getUserList();
        model.addAttribute("users", users);
        return "views/admin/user_manage";
    }

    @RequestMapping("/admin/user/to_add")
    public String user2Add(Model model) {
        return "views/admin/user_add";
    }

    @RequestMapping("/admin/user/add")
    public String userAdd(User user,
                          @RequestParam(value = "switch", defaultValue = "") String type,
                          Model model) {
        if (!type.equals("on")) {
            superService.addUser(user);
        }
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/to_edit")
    public String user2Edit(@RequestParam("user_id") Integer userId
            , Model model) {
        User user = superService.getUser(userId);
        model.addAttribute("user", user);
        return "views/admin/user_edit";
    }

    @RequestMapping("/admin/user/edit")
    public String userEdit(User user, Model model) {
        superService.editUser(user);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/del")
    public String userDel(@RequestParam("user_id") Integer userId) {
        superService.delUser(userId);
        return "redirect:/admin/user";
    }

    /*影厅列表*/
    @RequestMapping("admin/hall")
    public String hallManager(@RequestParam(value = "cinema_id", defaultValue = "1") Integer cinemaId, Model model) {
        List<CinemaInfo> cinemas = superService.getCinemaList();
        List<HallInfo> halls = superService.getHallsByCinema(cinemaId);

        model.addAttribute("cinemas", cinemas);
        model.addAttribute("halls", halls);
        model.addAttribute("cinemaId", cinemaId);

        return "views/admin/hall_manage";
    }

    /*添加影厅*/
    @RequestMapping("admin/hall/to_add")
    public String hall2Add(Model model) {
        List<CinemaInfo> cinemas = superService.getCinemaList();
        model.addAttribute("cinemas", cinemas);

        return "views/admin/hall_add";
    }

    @RequestMapping("admin/hall/add")
    public String hallAdd(@Param("row") Integer row,
                          @Param("col") Integer col,
                          @RequestParam(value = "cinema_id") Integer cinemaId,
                          @RequestParam(value = "name2") String hallName,
                          @RequestParam(value = "check") String[][] checks, Model model) {
//        System.out.println(row);
//        System.out.println(col);
//        System.out.println(hallName);
//        System.out.println(JSON.toJSONString(checks));

        superService.addHall(row, col, cinemaId, hallName, checks);
        return "redirect:/admin/hall";
    }

    @RequestMapping("admin/hall/del")
    public String hallDel(@RequestParam("hall_id") Integer hallId,Model model) {
        superService.delHall(hallId);
        return "redirect:/admin/hall";
    }

    @RequestMapping("/admin/test")
    public String test() {
        superService.getDate();
        return "";
    }

    /*ajax 渲染座位*/
    @RequestMapping("admin/hall/seat_set")
    public String hallSet(@Param("row") Integer row,
                          @Param("col") Integer col, Model model) {
        model.addAttribute("row", row);
        model.addAttribute("col", col);
        return "views/admin/seat_set";
    }

    @RequestMapping("admin/times")
    public String timesManager(@RequestParam(value = "cinema_id", defaultValue = "1") Integer cinemaId, Model model) {
        List<FilmTimes> times = superService.queryTimes(null, cinemaId, null);
        List<CinemaInfo> cinemas = superService.getCinemaList();
        List<HallInfo> halls = superService.getHallsByCinema(cinemaId);
        Map map = new HashMap();
        map.put("cinemaId", cinemaId);
        model.addAttribute("times", times);
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("halls", halls);
        model.addAttribute("map", map);
        return "views/admin/times_manage";
    }

    @RequestMapping("admin/times/to_add")
    public String times2Add(@RequestParam(value = "cinema_id", defaultValue = "1") Integer cinemaId,
                            @RequestParam(value = "film_id", defaultValue = "1") Integer filmId,
                            @RequestParam(value = "date", defaultValue = "05-28") String date,
                            @RequestParam(value = "time", defaultValue = "7") Integer time,
                            Model model) {
        List<String> dateList = superService.getDate();
        List<FilmInfo> films = filmService.selectAll();
        List<CinemaInfo> cinemas = superService.getCinemaList();
        List<HallInfo> halls = superService.getFreeHalls(cinemaId, date, time);
        Map map = new HashMap();
        map.put("cinemaId", cinemaId);
        map.put("filmId", filmId);
        map.put("date", date);
        map.put("time", time);

        model.addAttribute("films", films);
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("halls", halls);
        model.addAttribute("map", map);
        model.addAttribute("dateList", dateList);
        return "views/admin/times_add";
    }

    @RequestMapping("admin/times/to_add2")
    public String times2AddLocked(@RequestParam(value = "cinema_id", defaultValue = "1") Integer cinemaId,
                            @RequestParam(value = "film_id", defaultValue = "1") Integer filmId,
                            @RequestParam(value = "date", defaultValue = "05-28") String date,
                            @RequestParam(value = "time", defaultValue = "7") Integer time,
                            Model model) {
        List<String> dateList = superService.getDate();
        List<FilmInfo> films = filmService.selectAll();
        List<CinemaInfo> cinemas = superService.getCinemaList();
        List<HallInfo> halls = superService.getFreeHalls(cinemaId, date, time);
        Map map = new HashMap();
        map.put("cinemaId", cinemaId);
        map.put("filmId", filmId);
        map.put("date", date);
        map.put("time", time);

        model.addAttribute("films", films);
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("halls", halls);
        model.addAttribute("map", map);
        model.addAttribute("dateList", dateList);
        return "views/admin/times_add";
    }

    @RequestMapping("admin/times/add")
    public String timesAdd(@RequestParam(value = "hall_id") Integer hallId,
                           @RequestParam(value = "film_id") Integer filmId,
                           @RequestParam(value = "date") String date,
                           @RequestParam(value = "time") Integer time,
                           @RequestParam(value = "price") Float price,
                           Model model) {

        superService.addTimes(hallId, filmId, date, time, price);
        return "redirect:/admin/times";
    }

    @RequestMapping("admin/times/del")
    public String timesDel(@RequestParam(value = "time_id") Integer timeId,
                            Model model) {
        superService.delTime(timeId);
        return "redirect:/admin/times";
    }

    @RequestMapping("admin/times/seat")
    public String timesSeat(@RequestParam(value = "time_id", defaultValue = "1") Integer timeId,
                            Model model) {
        String[][] seats = orderService.getSeatByTimes(timeId);
        FilmTimes times = filmService.getFilmTime(timeId);

        model.addAttribute("times", times);
        model.addAttribute("seats", seats);
        return "views/admin/times_seat";
    }

    @RequestMapping("admin/films")
    public String filmsManager(@RequestParam(value = "cinema_id", defaultValue = "1") Integer cinemaId, Model model) {
        List<FilmInfo> films = filmService.selectAll();

        model.addAttribute("films", films);
        return "views/admin/films_manage";
    }

    @RequestMapping("admin/order")
    public String orderManager(@RequestParam(value = "cinema_id", defaultValue = "1") Integer cinemaId,
                               Model model) {
        List<OrderInfo> orders = superService.getAllOrder();
        List<CinemaInfo> cinemas = superService.getCinemaList();
        Map map = new HashMap();
        map.put("cinema_id", cinemaId);
        model.addAttribute("orders", orders);
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("map", map);
        return "views/admin/order_manage";
    }

    @RequestMapping("admin/order/off")
    public String orderOff(@RequestParam(value = "order_id") Integer orderId,
                           Model model) {
        superService.offOrder(orderId);
        return "redirect:/admin/order";
    }

    @RequestMapping("admin/order/ding")
    public String ding(@RequestParam(value = "phone") String phone) {
        superService.sendDing(phone);
        return "redirect:/admin/order";
    }
}
