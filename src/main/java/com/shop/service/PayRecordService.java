package com.shop.service;

import com.shop.domain.PayRecord;
import com.shop.domain.Product;

import java.util.Map;

/**
 * @PACKAGE_NAME: com.shop.service
 * @author: XIA
 * @NAME: PayRecordService
 * @DATE: 2020/1/22
 **/
public interface PayRecordService {
    int insert(PayRecord record);

    Map PayBill(Product product, String bid);
}
