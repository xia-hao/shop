package com.shop.service;

import com.shop.domain.Bill;
import com.shop.domain.Product;

import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shop.service
 * @author: XIA
 * @NAME: BillService
 * @DATE: 2020/1/21
 **/
public interface BillService {
    public int deleteByPrimaryKey(Integer id);

    public int insert(Bill record);

    public Map addBill(Product product);

    public Bill selectByPrimaryKey(Integer id);

    public List<Bill> selectByUid(Integer uid);

    public int updateByPrimaryKeySelective(Bill record);

    public int updateByStatus(Long diff,String statu);

    public int updateByPrimaryKey(Bill record);
}
