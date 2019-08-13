package com.springcloud_sell.userserver.vo;

import lombok.Data;

/**
 * @author haohan
 * 08/01/2019 - 04:40 下午
 * HTTP请求返回前端对象
 */
@Data
public class ResultVO<T> {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 具体数据
     */
    private T data;

}
