package com.shop.mapper;

import com.shop.domain.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {
    public int deleteByPrimaryKey(Integer id);

    public int insert(Bill record);

    public int insertSelective(Bill record);

    public Bill selectByPrimaryKey(Integer id);

    public List<Bill> selectByUid(Integer uid);

    public int updateByPrimaryKeySelective(Bill record);

    public int updateByPrimaryKey(Bill record);

    public int updateByStatus(@Param("diff") Long diff,@Param("statu") String statu);
}