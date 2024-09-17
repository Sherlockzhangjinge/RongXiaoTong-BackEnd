package com.qst.crop.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author shitusiren
 * @Date 2024/8/9
 */
@Data
public class FinaceUserDetails {

    private BigDecimal rate;

    private String phone;

    private String introduce;
    private String bankName;
}
