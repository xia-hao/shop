package com.shop.service.impl;

import com.shop.domain.User;
import com.shop.mapper.UserMapper;
import com.shop.service.UserService;
import com.shop.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shop.service.impl
 * @author: XIA
 * @NAME: UserServiceImpl
 * @DATE: 2020/1/15
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateUserBalance(User record) {
        return userMapper.updateUserBalance(record);
    }

    @Override
    public Map signIn(User user) {
        Map<String,Object> map = new HashMap<>();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        User user1 = null;
        try {
            subject.login(token);
            user1 = (User) subject.getPrincipal();
            map.put("user",user1);
            map.put("resCode","1000");
            map.put("info","登录成功！");
            rabbitTemplate.convertAndSend("OperLogExchange","OperLogRouting", map);
        } catch (Exception e) {
            map.put("resCode","902");
            map.put("info","账号或密码错误！");
        }finally {
            return map;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insertSelective(User user) {
        user.setPassword(MD5Utils.md5(user.getPassword()));
        return userMapper.insertSelective(user);
    }
}
