package com.qst.crop.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author spengda
 * @date 2024/3/714:10
 */
public class Testjiami {
    public static void main(String[] args) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
         String jiami = bCryptPasswordEncoder.encode("123456");
        System.out.println(jiami);


    }
}
