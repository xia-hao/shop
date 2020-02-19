package com.shop.mapper;

import com.shop.domain.PayRecord;

public interface PayRecordMapper {
    int insert(PayRecord record);

    int insertSelective(PayRecord record);
}