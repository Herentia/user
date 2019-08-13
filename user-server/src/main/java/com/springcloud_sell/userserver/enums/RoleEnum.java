package com.springcloud_sell.userserver.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author haohan
 * 08/13/2019 - 04:05 下午
 */
@AllArgsConstructor
@Getter
public enum RoleEnum {

    BUYER(1, "买家"),
    SELLER(2, "卖家"),
    ;

    private Integer code;
    private String message;

}
