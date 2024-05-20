package org.example.bstest.demos.bishe.controller;

import org.example.bstest.demos.bishe.DTO.RouteResponseDTO;

import org.example.bstest.demos.bishe.entity.UserEntity;
import org.example.bstest.demos.bishe.enums.ResponseStatusEnum;
import org.example.bstest.demos.bishe.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public RouteResponseDTO login(@RequestBody UserEntity userEntity) {

        RouteResponseDTO routeResponseDTO = authService.tryLogIn(userEntity.getUserName(), userEntity.getPassword());
        ResponseStatusEnum resStatus = routeResponseDTO.getResponseStatusEnum();
        if(ResponseStatusEnum.compare(resStatus, ResponseStatusEnum.FAIL)) {
            return routeResponseDTO;
        }
        // 生成token
        String token = generateToken(userEntity);

        // 将token设置到cookie中
        Cookie tokenCookie = new Cookie("auth_token", token);
        // 设置过期时间为1小时
        tokenCookie.setMaxAge(60 * 60 );
        // 如果使用HTTPS，则设置为true
        tokenCookie.setSecure(false);
        // 防止通过JavaScript访问cookie
        tokenCookie.setHttpOnly(true);

        // 响应中包含cookie
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        if (response != null) {
            response.addCookie(tokenCookie);
        }

        return RouteResponseDTO.ok(token);
    }

    private String generateToken(UserEntity userEntity) {
        // 不太安全，有时间改jwt实现
        return UUID.randomUUID().toString();
    }
}