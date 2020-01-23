package com.example.swagger.jwt.controller;

import com.example.swagger.jwt.commons.entity.UserInfo;
import com.example.swagger.jwt.commons.utils.CookieUtils;
import com.example.swagger.jwt.commons.utils.JwtProperties;
import com.example.swagger.jwt.commons.utils.JwtUtils;
import com.example.swagger.jwt.config.JwtConfig;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/21 20:54
 * @version:
 * @modified By:
 */
@RestController
@Slf4j

public class TokenController {
    /**
     * secret: ly@Login(Auth}*^31)&yun6%f3q2 # 登录校验的密钥
     * pubKeyPath: H:/javacode/idea/rsa/rsa.pub # 公钥地址
     * priKeyPath: H:/javacode/idea/rsa/rsa.pri # 私钥地址
     * expire: 30 # 过期时间,单位分钟
     * cookieName: LY_TOKEN
     */
    @Resource
    private JwtConfig jwtConfig;
    @Autowired
    private JwtProperties prop;
    @Value("${ly.jwt.ss}")
    private String ss;
    @GetMapping("/login")
    public JSONObject login(@RequestParam("userName") String userName,
                            @RequestParam("passWord") String passWord) {
        JSONObject json = new JSONObject();
        String userId = 5 + "";
        String token = jwtConfig.createToken(userId);
        json.put("token", token);
        return json;
    }

    @PostMapping("/login2")
    public ResponseEntity login2(@RequestParam("userName") String userName,
                                     @RequestParam("id") long passWord,
                                     HttpServletResponse response,
                                     HttpServletRequest request) throws FileNotFoundException {


        UserInfo userInfo = new UserInfo(passWord, userName);
        System.out.println("=============="+ss);
        //生成token
        String token = null;
        try {
            token = JwtUtils.generateToken(userInfo, prop.getPrivateKey(), prop.getExpire());
        } catch (Exception e) {
            log.error("[授权中心] 用户名或者密码有误，用户名称：{}", userInfo.getUsername(), e);
        }
        CookieUtils.newBuilder(response).httpOnly().request(request).build(prop.getCookieName(),token);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
