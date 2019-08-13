package com.springcloud_sell.userserver.repository;

import com.springcloud_sell.userserver.pojo.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author haohan
 * 08/13/2019 - 03:45 下午
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    /**
     * 通过openid查询用户信息
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);

}
