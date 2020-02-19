package com.shop.service;

import com.shop.domain.User;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shop.service
 * @author: XIA
 * @NAME: UserService
 * @DATE: 2020/1/15
 **/
public interface UserService {
    public int deleteByPrimaryKey(Integer id);

    public int insert(User record);

    public int insertSelective(User record);

    public User selectByPrimaryKey(Integer id);

    public User selectByName(String username);

    public int updateByPrimaryKeySelective(User record);

    public int updateByPrimaryKey(User record);
    
    public int updateUserBalance(User record);

    public Map signIn(User user);
}
