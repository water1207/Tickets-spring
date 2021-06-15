package cn.edu.hziee.tpp.service;

import cn.edu.hziee.tpp.mapper.UserMapper;
import cn.edu.hziee.tpp.model.User;
import cn.edu.hziee.tpp.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void sendSMS(String username) {
        User user = userMapper.selectByUserName(username);
        String phone = user.getPhone();
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
        querys.put("value", "3185");
        Map<String, String> bodys = new HashMap<String, String>();

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkSMS(String smsCode) {
        if (smsCode.equals("3185")) return true;
        return false;
    }

    public boolean checkPwd(User user) {
        User correct = userMapper.selectByUserName(user.getUserName());
        if (correct != null) {
            if (user.getUserPwd().equals(correct.getUserPwd())) {
                return true;
            }
            return false;
        }else{
            return false;
        }
    }

    public int checkLogin(String username,String pwd, String smsCode) {
        User user = new User();
        user.setUserName(username);
        user.setUserPwd(pwd);
        if (checkSMS(smsCode)) {
            if (checkPwd(user)) return 1;
            return 0;
        } else {
            return -1;
        }
    }

    public User getUser(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public Integer getUidByname(String name) {
        return userMapper.selectByUserName(name).getUserId();
    }

}
