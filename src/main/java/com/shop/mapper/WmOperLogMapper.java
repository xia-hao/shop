package com.shop.mapper;

import com.shop.domain.WmOperLog;

public interface WmOperLogMapper {
    int insert(WmOperLog record);

    int insertSelective(WmOperLog record);
}