package com.shop.mapper;

import com.shop.domain.Product;

import java.util.List;

public interface ProductMapper {
    public int deleteByPrimaryKey(Integer id);

    public int insert(Product record);

    public int insertSelective(Product record);

    public  Product selectByPrimaryKey(Integer id);

    public List<Product> selectByName(Product product);

    public int ProductTotal(Product product);

    public int updateByPrimaryKeySelective(Product record);

    public  int updateByPrimaryKey(Product record);

    public  int updateProNumMinus(Product record);
}