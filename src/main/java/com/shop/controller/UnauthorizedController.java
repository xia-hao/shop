package com.shop.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shop.controller
 * @author: XIA
 * @NAME: UnauthorizedController
 * @DATE: 2020/1/22
 **/
@CrossOrigin
@RestController
public class UnauthorizedController {

    @RequestMapping("/unauthorized")
    public Map unauthorized(){
        System.out.println("未授权");
        Map<String,String> map = new HashMap<>();
        map.put("error","您还未拥有此功能权限！");
        return map;
    }
}
