package com.shop.service.impl;

import com.shop.domain.User;
import com.shop.domain.WmOperLog;
import com.shop.mapper.WmOperLogMapper;
import com.shop.service.WmOperLogService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shop.service.impl
 * @author: XIA
 * @NAME: LogServiceImpl
 * @DATE: 2020/2/9
 **/
@Service
@RabbitListener(queues = "operLogQueue")
public class WmOperLogServiceImpl implements WmOperLogService {

    @Resource
    private WmOperLogMapper wmOperLogMapper;

    @RabbitHandler
    @Override
    public void getLogin(Map map) {
        WmOperLog wmOperLog = new WmOperLog();
        wmOperLog.setUid(((User)map.get("user")).getId());
        wmOperLog.setMotion(map.get("info").toString());
        wmOperLog.setCreationtime(new Date());
        wmOperLogMapper.insert(wmOperLog);
    }
}
