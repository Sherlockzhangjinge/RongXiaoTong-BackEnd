package com.qst.crop.dao;

import com.qst.crop.entity.Purchase;
import com.qst.crop.entity.SellPurchase;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PurchaseDao {

    int insertSelective(Purchase record);

    List<Purchase> selectBuys(String username);
    Purchase selectNewPurchaseId(String ownName);
    List<SellPurchase> selectByName(@Param("ownName") String ownName);
}
