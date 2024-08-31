package com.halcyon.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.halcyon.annotation.AdminPrefix;
import com.halcyon.dto.UserLoginDTO;
import com.halcyon.dto.UserRegisterDTO;
import com.halcyon.service.AuthService;
import com.halcyon.model.vo.ResponseResult;
import com.halcyon.vo.UserLoginVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-05-07 16:10
 * @description: 后台登录控制层
 */
@AdminPrefix
@RequiredArgsConstructor
@Slf4j
public class AdminAuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseResult<UserLoginVO> login(@Valid @RequestBody UserLoginDTO user, HttpServletRequest request){
        return ResponseResult.ok(authService.login(user,request));
    }

    @PostMapping("/register")
    public ResponseResult<Void> register( UserRegisterDTO user){
        // 未实现
        return ResponseResult.ok();
    }

    @PostMapping("/logout")
    public ResponseResult<Void> logout(){
        StpUtil.getTokenSession().clear();
        StpUtil.logout();
        return ResponseResult.ok();
    }
}
