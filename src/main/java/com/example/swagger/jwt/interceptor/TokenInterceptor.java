package com.example.swagger.jwt.interceptor;

import com.example.swagger.jwt.config.JwtConfig;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/21 20:40
 * @version:
 * @modified By:
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private JwtConfig jwtConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /** 地址过滤 */
//        String uri = request.getRequestURI();
//        if(uri.contains("/favicon.ico")){
//            return  true;
//        }
        /** Token 验证 */
        String token = request.getHeader(jwtConfig.getHeader());
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(jwtConfig.getHeader());
        }
        if(StringUtils.isEmpty(token)){
            throw new SignatureException(jwtConfig.getHeader()+ "不能为空");
        }
        Claims claims = null;
        try {
            claims = jwtConfig.getTokenClaim(token);
            if(claims == null || jwtConfig.isTokenExpired(claims.getExpiration())){
                throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
            }
        }catch (Exception e){
            throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
        }
        /** 设置 identityId 用户身份ID */
        request.setAttribute("identityId", claims.getSubject());
        return true;
    }
}
