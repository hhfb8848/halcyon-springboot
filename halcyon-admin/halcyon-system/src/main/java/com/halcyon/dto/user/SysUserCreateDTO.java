package com.halcyon.dto.user;

import com.halcyon.enums.BusinessStatusEnum;
import com.halcyon.enums.GenderEnum;
import com.halcyon.validator.EnumValue;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-07-02 15:07
 * @description: 用户创建对象
 */
@Data
public class SysUserCreateDTO {

    /**
     * 用户名（登录名）
     */
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "用户名必须为4至16位的字母、数字、下划线组成")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6到20个字符之间")
    private String password;

    /**
     * 邮箱号
     */
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 手机号
     */
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "手机号格式不正确")
    private String phone;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    @Size(max = 50, message = "昵称长度不能超过50个字符")
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 简介
     */
    @Size(max = 300, message = "简介长度不能超过300个字符")
    private String intro;

    /**
     * 性别（0未知，1男，2女）
     */
    @NotNull(message = "性别不能为空")
    @EnumValue(enumClass = GenderEnum.class, message = "性别只能为0（未知），1（男），2（女）")
    private Integer gender;

    /**
     * 出生日期
     */
    @Past(message = "出生日期必须是过去的时间")
    private LocalDateTime birthday;

    /**
     * 状态（0正常，1禁用）
     */
    @NotNull(message = "状态不能为空")
    @EnumValue(enumClass = BusinessStatusEnum.class, message = "状态只能为0（正常）或1（冻结）")
    private Integer status;
}
