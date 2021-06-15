package cn.edu.hziee.tpp.controller;

import cn.edu.hziee.tpp.model.FilmTimes;
import cn.edu.hziee.tpp.model.OrderInfo;
import cn.edu.hziee.tpp.model.OrderItemInfo;
import cn.edu.hziee.tpp.service.FilmService;
import cn.edu.hziee.tpp.service.OrderService;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private FilmService filmService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/user/film/seat")
    public String seatSelection(@RequestParam(value = "times_id",defaultValue = "1") Integer timeId,
                                HttpServletResponse response, HttpSession session,
                                Model model) {
        Integer userId =(Integer) session.getAttribute("uid");
        if (userId == null) {
            return "redirect:/login";
        }
        String[][] seats = orderService.getSeatByTimes(timeId);
        FilmTimes times = filmService.getFilmTime(timeId);
//        System.out.println(JSON.toJSONString(seats));
//        return seats;

        model.addAttribute("times", times);
        model.addAttribute("seats", seats);
        return "views/user/seats_selection";
    }

//    @RequestMapping("/user/order/confirm")
//    public String orderConfirm(@RequestParam(value = "times_id",defaultValue = "1") Integer timesId,
//                               @RequestParam(value = "check",defaultValue = "") String[] checks,Model model) {
//        FilmTimes times = filmService.getFilmTime(timesId);
//        OrderInfo order = orderService.confirmOrder(timesId, checks);
//        if (order == null) {
//            return "redirect:/user/film/seat?times_id="+timesId;
//        }
//        List<OrderItemInfo> items = order.getItemInfo();
//        System.out.println(items);
//        model.addAttribute("times",times);
//        model.addAttribute("order", order);
//        model.addAttribute("items", items);
//        return "views/user/confirm";
//    }
    @RequestMapping("/user/order/confirm2")
    public String orderConfirmLocked(@RequestParam(value = "times_id",defaultValue = "1") Integer timesId,
                               @RequestParam(value = "check",defaultValue = "") String[] checks,Model model) {
        FilmTimes times = filmService.getFilmTime(timesId);
        OrderInfo order = orderService.confirmOrderLocked(timesId, checks);
        if (order == null) {
            return "redirect:/user/film/seat?times_id="+timesId;
        }
        List<OrderItemInfo> items = order.getItemInfo();
        System.out.println(items);
        model.addAttribute("times",times);
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "views/user/confirm";
    }

    @RequestMapping("user/order/pay")
    public String orderPay(@RequestParam("order_id") Integer orderId) {
        orderService.orderPay(orderId);
        return "redirect:/user/order/mine";
    }

    @RequestMapping("/user/order/mine")
    public String myOrder(Model model, HttpServletResponse response, HttpSession session) {
        Integer userId =(Integer) session.getAttribute("uid");
        if (userId == null) {
            return "redirect:/login";
        }
        List<OrderInfo> orders = orderService.getOrderByUserId(userId);
        model.addAttribute("orders", orders);
        return "views/user/my_order";
    }

    @RequestMapping("demo")
    public void demo() {
        String[][] seats = orderService.getSeatByTimes(1);
    }
    @RequestMapping("/user/order/confirm")
    public String orderConfirm(@RequestParam(value = "times_id",defaultValue = "1") Integer timesId,
                               @RequestParam(value = "check",defaultValue = "") String[] checks,Model model) {
        FilmTimes times = filmService.getFilmTime(timesId);
        OrderInfo order = orderService.confirmOrder(timesId, checks);
        if (order == null) {
            return "redirect:/user/film/seat?times_id="+timesId;
        }
        List<OrderItemInfo> items = order.getItemInfo();
        System.out.println(items);
        model.addAttribute("times",times);
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "views/user/confirm";
    }
}
