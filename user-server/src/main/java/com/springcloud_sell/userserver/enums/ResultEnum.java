package com.springcloud_sell.userserver.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author haohan
 * 08/05/2019 - 02:44 下午
 */
@AllArgsConstructor
@Getter
public enum ResultEnum {

    LOGIN_FAIL(1, "登录失败"),
    ROLE_ERROR(2, "角色错误")
    ;

    private Integer code;
    private String msg;

}
