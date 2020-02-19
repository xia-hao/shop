package com.shop.controller;

import com.shop.domain.User;
import com.shop.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shop.controller
 * @author: XIA
 * @NAME: UserController
 * @DATE: 2020/1/15
 **/
@CrossOrigin  //实现跨域
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/login")
    public Map login() {
        Map<String, String> map = new HashMap<>();
        map.put("error", "您还未进行登录，是否跳转至登录页面进行登录？");
        map.put("data", "/login");
        return map;
    }

    @PostMapping("/signIn")
    public Map signIn(@RequestBody User user, HttpSession session) {
        Map<String, Object> map = userService.signIn(user);
        if(map.get("resCode").equals("1000")){
            session.setAttribute("user", map.get("user"));
        }
        return map;
    }

    @PostMapping("/register")
    public Map register(@RequestBody User user) {
        Map<String, String> map = new HashMap<>();
        try {
            userService.insertSelective(user);
            map.put("info", "注册成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("info", "注册失败！");
        } finally {
            map.put("data", "/login");
            return map;
        }

    }

    @GetMapping("/loginOut")
    public String loginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/login";
    }

    @PostMapping("/userinfo")
    public Map userinfo() {
        Map<String, Object> map = new HashMap<>();
        User u = (User) SecurityUtils.getSubject().getPrincipal();
        User user = null;
        if (null != u) {
            user = userService.selectByPrimaryKey(u.getId());
        }
        map.put("data", user);
        return map;
    }
}
