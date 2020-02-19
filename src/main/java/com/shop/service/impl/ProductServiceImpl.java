package com.shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.shop.domain.Product;
import com.shop.mapper.ProductMapper;
import com.shop.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PACKAGE_NAME: com.shop.service.impl
 * @author: XIA
 * @NAME: ProductServiceImpl
 * @DATE: 2020/1/18
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insertSelective(Product record) {
        return productMapper.insertSelective(record);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Product selectByPrimaryKey(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Product> selectByName(Product product) {
        PageHelper.startPage(product.getPageNum(),product.getPageSize());
        return productMapper.selectByName(product);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int ProductTotal(Product product) {
        return productMapper.ProductTotal(product);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateByPrimaryKeySelective(Product record) {
        return productMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateProNumMinus(Product record) {
        return productMapper.updateProNumMinus(record);
    }
}
