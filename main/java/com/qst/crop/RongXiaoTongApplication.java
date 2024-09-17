package com.qst.crop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author shitusiren
 * @Date 2024/8/9
 */
@SpringBootApplication
@MapperScan(basePackages = "com.qst.crop.dao")
public class RongXiaoTongApplication {

    public static void main(String[] args) {
        SpringApplication.run(RongXiaoTongApplication.class,args);
    }

    @Bean
    PasswordEncoder passwordEncoder (){

        return new BCryptPasswordEncoder();
    }
}
