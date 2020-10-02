package com.gupaoedu.springbootbeanvalidation.domain;

import com.gupaoedu.springbootbeanvalidation.validation.constraints.ValidCardNumber;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * 用户模型
 *
 * @Author: LIH
 * @Date: 2020/9/12 21:42
 */
public class User {

    @Max(value = 10000)
    private long id;

    @NotNull
    private String name;

    //卡号 -- GUPAO-123456789
    @NotNull
//    @ValidCardNumber(message = "卡号必须以\"GUPAO\"开头，以数字结尾")
    @ValidCardNumber
    private String cardNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
