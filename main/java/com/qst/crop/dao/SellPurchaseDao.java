package com.qst.crop.dao;

import com.qst.crop.entity.SellPurchase;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellPurchaseDao {
    int insertSelective(SellPurchase record);
    List<SellPurchase> selectSells(String userName);
}

