package com.shop.quartz;

import com.shop.domain.Bill;
import com.shop.service.BillService;
import com.shop.utils.Constant;
import org.apache.tomcat.util.bcel.Const;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @PACKAGE_NAME: com.shop.quartz
 * @author: XIA
 * @NAME: Job
 * @DATE: 2020/1/23
 **/
public class Job implements org.quartz.Job {
    @Resource
    private BillService billService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 取消超时的订单
        System.out.println("定时任务开启");
        // 参数一： 订单超过十五分钟未支付则更改状态
        // 参数二： 订单状态
        int count = billService.updateByStatus(15l, Constant.Bill_CANCEL);
        if (count > 0) {
            System.out.println("超时订单已取消：“" + count/2 + "”条");
        }
    }
}
