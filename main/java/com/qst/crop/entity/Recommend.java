package com.qst.crop.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigInteger;
import java.util.List;

/**
 * @author shitusiren
 * @Date 2024/8/9
 */
@Data
public class Recommend {
    private String userName;
    private String realName;
    private String phone;
    private String address;

    private String avatar;
    private Integer area;

    private String item;

    private String amount;
    private Integer num;
}
