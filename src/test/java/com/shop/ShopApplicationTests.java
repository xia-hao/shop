package com.shop;

import com.shop.domain.Product;
import com.shop.domain.User;
import com.shop.service.ProductService;
import com.shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ShopApplicationTests {

    @Resource
    private UserService userService;
    @Resource
    private ProductService productService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("admin");
        System.out.println("======"+userService.selectByName(user.getUsername()).getUsername());
    }
    @Test
    void test1() {
        Product product = new Product();
        product.setPageNum(1);
        product.setPageSize(5);
        product.setProName("电");
        List<Product> list = productService.selectByName(product);
       for (Product pro : list){
           System.out.println(pro.getId()+"======"+pro.getProName());
       }
    }

}
