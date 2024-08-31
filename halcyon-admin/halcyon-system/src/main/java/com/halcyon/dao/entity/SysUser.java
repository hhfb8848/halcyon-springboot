package com.halcyon.dao.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 用户信息表(SysUser)表实体类
 *
 * @author sjh
 * @since 2024-04-24 10:35:56
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUser {
    /**
     * 主键（用户id）
     */
    @TableId
    private Long id;

    /**
     * 用户名（登录名）
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱号
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 简介
     */
    private String intro;

    /**
     * 性别（0未知，1男，2女）
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 状态（0正常，1禁用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 上次登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 最后登录IP地址
     */
    private String ipAddress;

    /**
     * 最后登录IP来源
     */
    private String ipSource;

    /**
     * 注册来源
     */
    private String registerSource;

    /**
     * 删除标志（0否 1是）
     */
    @TableLogic
    private Integer delFlag;

}

