package com.springcloud_sell.userserver.service;

import com.springcloud_sell.userserver.pojo.UserInfo;

/**
 * @author haohan
 * 08/13/2019 - 03:48 下午
 */
public interface UserService {

    public UserInfo findByOpenid(String openid);

}
