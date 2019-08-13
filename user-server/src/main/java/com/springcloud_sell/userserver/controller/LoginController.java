package com.springcloud_sell.userserver.controller;

import com.springcloud_sell.userserver.constant.CookieConstant;
import com.springcloud_sell.userserver.constant.RedisConstant;
import com.springcloud_sell.userserver.enums.ResultEnum;
import com.springcloud_sell.userserver.enums.RoleEnum;
import com.springcloud_sell.userserver.pojo.UserInfo;
import com.springcloud_sell.userserver.service.UserService;
import com.springcloud_sell.userserver.utils.CookieUtil;
import com.springcloud_sell.userserver.utils.ResultVOUtil;
import com.springcloud_sell.userserver.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author haohan
 * 08/13/2019 - 03:58 下午
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam String openid,
                          HttpServletResponse response) {
        //openid和数据库里面的数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        //判断角色
        if(RoleEnum.BUYER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        //将openid存到cookie里面
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.EXPIRE);

        return ResultVOUtil.success();

    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam String openid,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        //判断用户是否已经登录
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKNE);
        if(cookie != null &&
                !StringUtils.isEmpty(redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
            return ResultVOUtil.success();
        }

        //openid和数据库里面的数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //判断角色
        if(RoleEnum.SELLER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        //将openid存入redis， key=UUID， value=openid
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token), openid,
                expire, TimeUnit.SECONDS);

        //cookie里设置token=UUID
        CookieUtil.set(response, CookieConstant.TOKNE, token, CookieConstant.EXPIRE);

        return ResultVOUtil.success();
    }

}
