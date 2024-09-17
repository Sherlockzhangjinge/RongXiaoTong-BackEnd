package com.qst.crop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author HP
 * @date 2024/8/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SellPurchase {
    private Integer sellPurchaseId;
    private Integer purchaseId;
    private String ownName;
    private Integer purchaseType;
    private BigDecimal uninPricee;
    private BigDecimal sumPrice;
    private String address;
    private Integer purchaseStatus;
    private Date createTime;
    private Date updateTime;
    private Integer orderId;
}
