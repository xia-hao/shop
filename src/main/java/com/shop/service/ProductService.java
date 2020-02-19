package com.shop.service;

import com.shop.domain.Product;

import java.util.List;

/**
 * @PACKAGE_NAME: com.shop.service
 * @author: XIA
 * @NAME: ProductService
 * @DATE: 2020/1/18
 **/
public interface ProductService {
    public int deleteByPrimaryKey(Integer id);

    public int insertSelective(Product record);

    public  Product selectByPrimaryKey(Integer id);

    public List<Product> selectByName(Product product);

    public int ProductTotal(Product product);

    public int updateByPrimaryKeySelective(Product record);

    public  int updateProNumMinus(Product record);
}
