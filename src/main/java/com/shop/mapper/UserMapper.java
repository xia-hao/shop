package com.shop.mapper;

import com.shop.domain.User;

import java.util.List;

public interface UserMapper {
    public int deleteByPrimaryKey(Integer id);

    public int insert(User record);

    public int insertSelective(User record);

    public User selectByPrimaryKey(Integer id);

    public User selectByName(String username);

    public int updateByPrimaryKeySelective(User record);

    public int updateByPrimaryKey(User record);

    public int updateUserBalance(User record);
}