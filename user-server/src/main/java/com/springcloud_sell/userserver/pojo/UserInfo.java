package com.springcloud_sell.userserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author haohan
 * 08/13/2019 - 03:39 下午
 * 用户实体类
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserInfo implements Serializable {

    @Id
    private String id;
    private String username;
    private String password;
    private String openid;
    private Integer role;

}
