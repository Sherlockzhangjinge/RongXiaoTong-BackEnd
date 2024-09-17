package com.qst.crop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name ="tb_purchase")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Purchase {
    private Integer purchaseId;
    private String ownName;
private Integer purchaseType;
private BigDecimal totalPrice;
private String address;
private Integer purchaseStatus;
private Date createTime;
private Date updateTime;
    private String picture;


}
