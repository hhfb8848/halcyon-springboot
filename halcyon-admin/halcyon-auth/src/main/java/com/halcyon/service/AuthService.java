package com.halcyon.service;

import com.halcyon.dto.UserLoginDTO;
import com.halcyon.dto.UserRegisterDTO;
import com.halcyon.vo.UserLoginVO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-05-09 10:39
 * @description: 账号相关
 */
public interface AuthService {

    /**
     * 注册
     * @param userRegisterDTO 参数
     */
    void register(UserRegisterDTO userRegisterDTO);

    /**
     * 登录
     * @param userLoginDTO 参数
     * @return 返回
     */
    UserLoginVO login(UserLoginDTO userLoginDTO);
}
