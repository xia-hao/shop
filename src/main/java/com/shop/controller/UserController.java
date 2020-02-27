package com.shop.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.shop.domain.User;
import com.shop.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    @Autowired
    private DefaultKaptcha defaultKaptcha;

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
        if(!map.get("resCode").equals("902")&&!user.getCode().equals(session.getAttribute("rightCode"))){
            map.put("resCode","905");
            map.put("info","验证码错误！");
        }
        if(map.get("resCode").equals("1000")){
            session.setAttribute("user", map.get("user"));
            map.put("data","/index");
        }
        return map;
    }

    @RequestMapping("/img")
    public void img(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("rightCode", createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);

            // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
            captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
            httpServletResponse.setHeader("Cache-Control", "no-store");
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setContentType("image/jpeg");
            ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
            responseOutputStream.write(captchaChallengeAsJpeg);
            responseOutputStream.flush();
            responseOutputStream.close();

        } catch (IllegalArgumentException e) {
            return;
        } catch (IOException e) {
            return;
        }

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
