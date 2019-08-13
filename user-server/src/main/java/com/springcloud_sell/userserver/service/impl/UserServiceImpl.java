package com.springcloud_sell.userserver.service.impl;

import com.springcloud_sell.userserver.pojo.UserInfo;
import com.springcloud_sell.userserver.repository.UserInfoRepository;
import com.springcloud_sell.userserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author haohan
 * 08/13/2019 - 03:49 下午
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
