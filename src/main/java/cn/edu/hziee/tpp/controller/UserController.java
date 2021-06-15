package cn.edu.hziee.tpp.controller;

import cn.edu.hziee.tpp.model.User;
import cn.edu.hziee.tpp.service.UserService;
import cn.edu.hziee.tpp.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login/sms/send")
    public void send(@RequestParam("user_name") String userName) {
        userService.sendSMS(userName);
    }
//
    @RequestMapping("/login")
    public String login() {
        return "views/user/login";
    }

    @RequestMapping("/login/check")
    public String checkLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("sms_code") String smsCode,
                             HttpServletResponse response, HttpSession session) {
        if (userService.checkLogin(username,password, smsCode) == 1) {
            session.setAttribute("uid",userService.getUidByname(username));
            session.setAttribute("userInfo", "logining");
            return "redirect:/user/index";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping("/user/profile")
    public String profile(HttpServletResponse response, HttpSession session,Model model) {
        Integer userId =(Integer) session.getAttribute("uid");
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "views/user/profile";
    }
}
