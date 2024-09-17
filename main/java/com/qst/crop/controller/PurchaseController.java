package com.qst.crop.controller;

import com.qst.crop.common.Result;
import com.qst.crop.common.StatusCode;
import com.qst.crop.entity.Purchase;
import com.qst.crop.security.entity.JwtUser;
import com.qst.crop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/selectBuys")
    public Result<List<Purchase>> selectBuys() {
        JwtUser principal = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        return new Result<>(true, StatusCode.OK,"查询成功",purchaseService.selectBuys(username));
    }

}
