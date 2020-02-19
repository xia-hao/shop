package com.shop.controller;

import com.shop.domain.Product;
import com.shop.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shop.controller
 * @author: XIA
 * @NAME: ProductController
 * @DATE: 2020/1/19
 **/
@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @GetMapping("/list")
    public Map list(Product product){
        Map<String,Object> map = new HashMap<>();
        List<Product> list = productService.selectByName(product);
        int total = productService.ProductTotal(product);
        map.put("data",list);
        map.put("total",total);
        return map;
    }
    @GetMapping("/productById/{id}")
    public Map productById(@PathVariable Integer id){
        Map<String,Object> map = new HashMap<>();
        Product product = productService.selectByPrimaryKey(id);
        map.put("data",product);
        return map;
    }
}
