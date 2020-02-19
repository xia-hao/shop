package com.shop.controller;

import com.shop.domain.Bill;
import com.shop.domain.Product;
import com.shop.domain.User;
import com.shop.service.BillService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @PACKAGE_NAME: com.shop.controller
 * @author: XIA
 * @NAME: BillController
 * @DATE: 2020/1/21
 **/
@CrossOrigin
@RestController
@RequestMapping("/bill")
public class BillController {
    @Resource
    private BillService billService;

    @GetMapping("/add")
    public Map addBill(Product product){
        Map<String,Object> map = billService.addBill(product);
        return map;
    }

    @GetMapping("/list")
    public Map list(){
        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        map.put("bill",billService.selectByUid(user.getId()));
        return map;
    }

}
