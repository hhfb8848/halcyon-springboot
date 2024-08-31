package com.halcyon.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halcyon.exception.ServiceException;
import com.halcyon.utils.AsyncEmailUtils;
import com.halcyon.utils.RedisCacheUtils;
import com.halcyon.dao.entity.SysUser;
import com.halcyon.dto.account.EmailUpdateDTO;
import com.halcyon.dto.account.PasswordUpdateDTO;
import com.halcyon.service.AccountSettingService;
import com.halcyon.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.halcyon.constant.Constants.PLATFORM_NAME;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-08-03 22:31
 * @description: 用户账号
 */
@Service
@RequiredArgsConstructor
public class AccountSettingServiceImpl implements AccountSettingService {

    private static final String UPDATE_PASSWORD_KEY = "user.account.update.password:";

    private static final String UPDATE_EMAIL_KEY = "user.account.update.email:";

    public static final Integer CODE_EXPIRE_TIME = 10 * 60;

    private final SysUserService userService;

    private final RedisCacheUtils redisCacheUtils;

    private final AsyncEmailUtils emailUtils;

    /**
     * 发送验证码，修改密码或邮箱用
     * @param email 为空则是修改密码
     */
    @Override
    public void sendCode(String email){
        Long loginId = Long.valueOf(StpUtil.getLoginId().toString());
        // 改密码
        if (Objects.isNull(email)){
            SysUser user = userService.getById(loginId);
            if (Objects.isNull(user.getEmail())){
                throw new ServiceException("此账号未绑定邮箱");
            }
            // 生成六位随机验证码发送
            String verificationCode = RandomUtil.randomNumbers(6);
            emailUtils.sendEmailAsync(user.getEmail(),PLATFORM_NAME.concat("-密码修改"),verificationCode);

            // 将验证码存入redis，设置过期时间为10分钟
            redisCacheUtils.setCacheObject(UPDATE_PASSWORD_KEY.concat(user.getEmail()).concat(loginId.toString()), verificationCode, CODE_EXPIRE_TIME, TimeUnit.SECONDS);

        }else{ // 改邮箱
            SysUser user = userService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getEmail, email));
            if (Objects.nonNull(user)){
                throw new ServiceException("该邮箱已被绑定");
            }
            // 生成六位随机验证码发送
            String verificationCode = RandomUtil.randomNumbers(6);
            emailUtils.sendEmailAsync(email,PLATFORM_NAME.concat("-邮箱绑定"),verificationCode);
            redisCacheUtils.setCacheObject(UPDATE_EMAIL_KEY.concat(email).concat(loginId.toString()), verificationCode, CODE_EXPIRE_TIME, TimeUnit.SECONDS);
        }
    }

    @Override
    public void updatePassword(PasswordUpdateDTO updateDTO) {
        Long loginId = Long.valueOf(StpUtil.getLoginId().toString());
        SysUser user = userService.getById(loginId);
        if (Objects.isNull(user.getEmail())){
            throw new ServiceException("此账号未绑定邮箱");
        }
        String cacheCode = redisCacheUtils.getCacheObject(UPDATE_PASSWORD_KEY.concat(user.getEmail()).concat(loginId.toString()));
        if (Objects.isNull(cacheCode)){
            throw new ServiceException("验证码已失效，请重新获取");
        }
        if (!cacheCode.equals(updateDTO.getVerificationCode())){
            throw new ServiceException("验证码错误");
        }
        SysUser sysUser = new SysUser();
        sysUser.setId(loginId);
        sysUser.setPassword(SaSecureUtil.md5(updateDTO.getNewPassword()));
        user.setUpdateBy(loginId);
        userService.updateById(sysUser);
    }

    @Override
    public void updateEmail(EmailUpdateDTO updateDTO) {
        Long loginId = Long.valueOf(StpUtil.getLoginId().toString());
        String cacheCode = redisCacheUtils.getCacheObject(UPDATE_EMAIL_KEY.concat(updateDTO.getNewEmail()).concat(loginId.toString()));
        if (Objects.isNull(cacheCode)){
            throw new ServiceException("验证码已失效，请重新获取");
        }
        if (!cacheCode.equals(updateDTO.getVerificationCode())){
            throw new ServiceException("验证码错误");
        }
        SysUser user = userService.getById(loginId);
        user.setEmail(updateDTO.getNewEmail());
        user.setUpdateBy(loginId);
        userService.updateById(user);
    }
}
