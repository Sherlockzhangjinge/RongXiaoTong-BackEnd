package com.qst.crop.service.impl;

import com.qst.crop.dao.PurchaseDao;
import com.qst.crop.entity.Purchase;
import com.qst.crop.entity.SellPurchase;
import com.qst.crop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseDao purchaseDao;

    @Override
    public void add(Purchase purchase) {
        purchaseDao.insertSelective(purchase);
    }

    @Override
    public List<Purchase> selectBuys(String username) {
        return purchaseDao.selectBuys(username);
    }

    @Override
    public Purchase selectNewPurchaseId(String ownName) {
        return purchaseDao.selectNewPurchaseId(ownName);
    }

    @Override
    public List<SellPurchase> selectByName(){
        // 获取用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();

        return purchaseDao.selectByName(name);
    }
}
