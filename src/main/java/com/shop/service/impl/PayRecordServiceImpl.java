package com.shop.service.impl;

import com.shop.domain.Bill;
import com.shop.domain.PayRecord;
import com.shop.domain.Product;
import com.shop.domain.User;
import com.shop.mapper.PayRecordMapper;
import com.shop.service.BillService;
import com.shop.service.PayRecordService;
import com.shop.service.ProductService;
import com.shop.service.UserService;
import com.shop.utils.Constant;
import org.apache.shiro.SecurityUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.Bidi;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shop.service.impl
 * @author: XIA
 * @NAME: PayRecordServiceImpl
 * @DATE: 2020/1/22
 **/
@Service
public class PayRecordServiceImpl implements PayRecordService {
    @Resource
    private PayRecordMapper payRecordMapper;
    @Resource
    private UserService userService;
    @Resource
    private BillService billService;
    @Resource
    private ProductService productService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insert(PayRecord record) {
        return payRecordMapper.insert(record);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map PayBill(Product product, String bid) {
        Integer userId = ((User) SecurityUtils.getSubject().getPrincipal()).getId();
        Map<String, Object> map = new HashMap<>();
        map.put("resCode", "500");
        map.put("info", "请求错误！");

        PayRecord payRecord = new PayRecord();
        payRecord.setBid(bid);
        payRecord.setUid(userId);
        payRecord.setFrontSum(product.getPrice());
        payRecord.setLastSum((product.getPrice() * product.getNumber()));
        payRecord.setPayDate(new Date());

        User user = new User();
        user.setId(userId);
        user.setBalance(Long.valueOf(payRecord.getLastSum()));

        Bill bill = new Bill();
        bill.setId(payRecord.getBid());
        bill.setBillStatus(Constant.Bill_PAY); //已支付状态

        if (userService.updateUserBalance(user) > 0) {  //修改用户金额，修改成功则用户余额足以支付商品价格
            billService.updateByPrimaryKeySelective(bill); //修改订单状态
            payRecordMapper.insertSelective(payRecord); // 添加支付信息
            map.put("resCode", "1000");
            map.put("info", "支付成功！");
            map.put("user",((User)SecurityUtils.getSubject().getPrincipal()));
            rabbitTemplate.convertAndSend("OperLogExchange","OperLogRouting", map);
        } else {
            map.put("resCode", "902");
            map.put("info", "支付失败，余额不足！");
        }
        return map;
    }
}
