package com.shop.controller;

import com.shop.domain.PayRecord;
import com.shop.domain.Product;
import com.shop.domain.User;
import com.shop.service.BillService;
import com.shop.service.PayRecordService;
import com.shop.service.ProductService;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shop.controller
 * @author: XIA
 * @NAME: PayController
 * @DATE: 2020/1/22
 **/
@CrossOrigin
@RestController
@RequestMapping("/pay")
public class PayController {
    @Resource
    private PayRecordService payRecordService;

    @GetMapping("/paybill/{bid}")
    public Map paybill(Product product, @PathVariable String bid) {
        Map<String, Object> map = payRecordService.PayBill(product,bid);
        map.put("data", "/commoditylist");
        return map;
    }
}
