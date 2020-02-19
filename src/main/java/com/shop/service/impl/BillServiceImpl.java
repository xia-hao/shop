package com.shop.service.impl;

import com.shop.domain.Bill;
import com.shop.domain.Product;
import com.shop.domain.User;
import com.shop.mapper.BillMapper;
import com.shop.mapper.ProductMapper;
import com.shop.service.BillService;
import com.shop.service.ProductService;
import com.shop.utils.Constant;
import com.shop.utils.redis.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shop.service.impl
 * @author: XIA
 * @NAME: BillServiceImpl
 * @DATE: 2020/1/21
 **/

@Service
public class BillServiceImpl implements BillService {
    @Resource
    private BillMapper billMapper;

    @Resource
    private ProductService productService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Resource
    private RedisUtil redisUtil;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return billMapper.deleteByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insert(Bill record) {
        return billMapper.insert(record);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map addBill(Product product) {
        Map<String,Object> map = new HashMap<>();

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Date data1 = new Date();
        String code = data1.getTime()+"";

        Bill bill = new Bill();
        bill.setId(code);
        bill.setPid(product.getId());
        bill.setUid(user.getId());
        bill.setMoney(product.getPrice());
        bill.setBillStatus(Constant.Bill_STAY);
        bill.setBillDate(new Date());

        Product p = new Product();
        p.setId(product.getId());
        p.setNumber(1);
        Boolean getLock = redisUtil.getLock(product.getId()+"_addOrder", 10 * 1000);
        while(!getLock){
            System.out.println("当前商品有其他人在排队购买,继续等待...");
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {

            }
            getLock = redisUtil.getLock(product.getId()+"_addOrder", 10 * 1000);

        }
        if(getLock){
            System.out.println("获得锁,继续执行");
        }
        if (productService.updateProNumMinus(p) > 0) { //修改商品库存,修改成功则库存大于1，否则商品已卖完，暂无库存
            billMapper.insertSelective(bill);
            map.put("resCode","1000");
            map.put("info", "订单添加成功！");
            map.put("code",code);
            redisUtil.releaseLock(product.getId()+"_addOrder");

            map.put("user",((User)SecurityUtils.getSubject().getPrincipal()));
            rabbitTemplate.convertAndSend("OperLogExchange","OperLogRouting", map);
        }else{
            map.put("resCode","901");
            map.put("info", "商品库存不足，请下次再来！");
        }
        map.put("page","/pay");
        return map;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Bill selectByPrimaryKey(Integer id) {
        return billMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Bill> selectByUid(Integer uid) {
        return billMapper.selectByUid(uid);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateByPrimaryKeySelective(Bill record) {
        return billMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateByStatus(Long diff,String statu) {
        return billMapper.updateByStatus(diff,statu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateByPrimaryKey(Bill record) {
        return billMapper.updateByPrimaryKey(record);
    }
}
