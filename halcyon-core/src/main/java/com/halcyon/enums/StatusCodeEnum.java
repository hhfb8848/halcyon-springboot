package com.halcyon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sjh
 * @date 2023-1-14 13:51
 * @description: 系统各种状态枚举
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum StatusCodeEnum {
    /**
     * 成功
     */
    SUCCESS("H200", "请求成功"),

    /**
     * 验证码错误
     */
    VERIFICATIONCODE_ERROR("H400", "验证码错误"),

    /**
     * 未登录
     */
    NO_LOGIN("H401", "用户未登录"),
    /**
     * 没有操作权限
     */
    AUTHORIZED("H403", "无操作权限"),

    /**
     * 404
     */
    NO_FOUND("H404","系统无此资源"),
    /**
     * 系统异常
     */
    SYSTEM_ERROR("H500", "系统错误"),
    /**
     * 操作失败
     */
    FAIL("H510", "操作失败"),
    /**
     * 参数格式不正确
     */
    VALID_ERROR("H520", "参数校验失败"),
    /**
     * 上传失败
     */
    UPLOAD_FAIL("H530", "上传失败"),
    /**
     * 创建目录失败
     */
    CREATE_MKR_FAIL("H540", "创建目录失败"),
    /**
     * 用户已存在
     */
    USER_EXIST("H610", "用户已存在"),
    /**
     * 用户不存在
     */
    USER_NOT_EXIST("H620", "用户不存在"),

    /**
     * 账号已被冻结
     */
    USER_FREEZE("H630","账号已被冻结"),

    /**
     * 账号无权限进入后台
     */
    USER_NO_ACCESS("H640","此账号无权限进入后台"),

    /**
     * 角色已被冻结
     */
    ROLE_FREEZE("H630","角色已被禁用"),


    /**
     * 常规登录错误
     */
    LOGIN_ERROR("H700", "用户名或密码错误"),
    /**
     * qq登录错误
     */
    QQ_LOGIN_ERROR("H710", "qq登录错误"),
    /**
     * 微博登录错误
     */
    WEIBO_LOGIN_ERROR("H720", "微博登录错误"),

    /**
     * token验证错误
     */
    TOKEN_ERROR("H800", "token验证错误");

    /**
     * 状态码
     */
    private final String code;

    /**
     * 描述
     */
    private final String message;

}
