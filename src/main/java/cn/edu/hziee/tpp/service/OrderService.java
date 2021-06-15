package cn.edu.hziee.tpp.service;

import cn.edu.hziee.tpp.mapper.*;
import cn.edu.hziee.tpp.model.FilmTimes;
import cn.edu.hziee.tpp.model.OrderInfo;
import cn.edu.hziee.tpp.model.OrderItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private CinemaInfoMapper cinemaInfoMapper;
    @Autowired
    private FilmInfoMapper filmInfoMapper;
    @Autowired
    private FilmTimesMapper filmTimesMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderItemInfoMapper orderItemMapper;

    public OrderInfo getOrder(Integer orderId) {
        return orderInfoMapper.selectByPrimaryKey(orderId);
    }

    public String[][] getSeatByTimes(Integer timesId) {
        FilmTimes filmTimes = filmTimesMapper.selectByPrimaryKey(timesId);
        String[] original_seat = filmTimes.getHallInfo().getSeatInfo().split(",");

        List<OrderInfo> orderInfo = orderInfoMapper.selectByTimesId(timesId);
        List<OrderItemInfo> none_seat = new ArrayList<>();
        for (OrderInfo order :
                orderInfo) {
            List<OrderItemInfo> items = order.getItemInfo();
            for (OrderItemInfo item :
                    items) {
                none_seat.add(item);
            }
        }

        String[][] seats = new String[original_seat.length][];
        for (int i = 0; i < original_seat.length; i++) {
            String temp[] = original_seat[i].split("");
            seats[i] = temp;
            for (int j = 0; j < temp.length; j++) {
                for (int k = 0; k < none_seat.size(); k++) {
                    if (none_seat.get(k).getPosX() == i && none_seat.get(k).getPosY() == j) {
                        seats[i][j] = "b";
                    }
                }
            }
        }
        //System.out.println(itemList);
        return seats;
    }

    public OrderInfo confirmOrder(Integer timesId, String[] check) {
        List<OrderInfo> orders = orderInfoMapper.selectByTimesIdLocked(timesId);
        for (OrderInfo order:
             orders) {
            for (OrderItemInfo item:
                 order.getItemInfo()) {
                for (int i = 0; i < check.length; i += 2) {
                    if (item.getPosX() == Integer.valueOf(check[i]) && item.getPosY() == Integer.valueOf(check[i + 1])) {
                        return null;
                    }
                }
            }
        }

        FilmTimes filmTimes = filmTimesMapper.selectByPrimaryKey(timesId);
        float total = check.length / 2 * filmTimes.getFilmPrice();

        OrderInfo order = new OrderInfo();
        order.setUserId(1);
        order.setTotal(Float.toString(total));
        order.setFilmTimesId(timesId);
        order.setOrderState(0);
        order.setPhone("15268526658");
        orderInfoMapper.insert(order);
        Integer orderId = order.getOrderId();

        for (int i = 0; i < check.length; i += 2) {
            OrderItemInfo orderItem = new OrderItemInfo();
            orderItem.setOrderId(orderId);
            orderItem.setPosX(Integer.valueOf(check[i]));
            orderItem.setPosY(Integer.valueOf(check[i + 1]));
            orderItemMapper.insert(orderItem);
        }
        order = orderInfoMapper.selectByPrimaryKey(orderId);
        return order;
    }

    public OrderInfo confirmOrderLocked(Integer timesId, String[] check) {
        FilmTimes times = filmTimesMapper.selectByPrimaryKeyLocked(timesId);
        String[] original_seat = times.getSeatInfo().split(",");
        String[][] seats = new String[original_seat.length][];
        for (int i = 0; i < original_seat.length; i++) {
            String temp[] = original_seat[i].split("");
            seats[i] = temp;
            for (int j = 0; j < temp.length; j++) {
                for (int k = 0; k < check.length; k++) {
                    if (i == Integer.valueOf(check[i]) && j == Integer.valueOf(check[i + 1])) {
                        return null;
                    }
                }

            }
        }

        float total = check.length / 2 * times.getFilmPrice();

        OrderInfo order = new OrderInfo();
        order.setUserId(1);
        order.setTotal(Float.toString(total));
        order.setFilmTimesId(timesId);
        order.setOrderState(0);
        order.setPhone("15268526658");
        orderInfoMapper.insert(order);
        Integer orderId = order.getOrderId();

        for (int i = 0; i < check.length; i += 2) {
            OrderItemInfo orderItem = new OrderItemInfo();
            orderItem.setOrderId(orderId);
            orderItem.setPosX(Integer.valueOf(check[i]));
            orderItem.setPosY(Integer.valueOf(check[i + 1]));
            orderItemMapper.insert(orderItem);
        }
        order = orderInfoMapper.selectByPrimaryKey(orderId);
        return order;
    }

    public List<OrderInfo> getOrderByUserId(Integer userId) {
        return orderInfoMapper.selectByUserId(userId);
    }

    public void orderPay(Integer orderId) {
        OrderInfo order = orderInfoMapper.selectByPrimaryKey(orderId);
        order.setOrderState(1);
        orderInfoMapper.updateByPrimaryKey(order);
    }


}
