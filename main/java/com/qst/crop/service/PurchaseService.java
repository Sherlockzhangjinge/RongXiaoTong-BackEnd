package com.qst.crop.service;

import com.qst.crop.entity.Purchase;
import com.qst.crop.entity.SellPurchase;

import java.util.List;

public interface PurchaseService {
    void add(Purchase purchase);

    List<Purchase> selectBuys(String username);
    Purchase selectNewPurchaseId(String ownName);
    List<SellPurchase> selectByName();
}
