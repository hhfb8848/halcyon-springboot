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
 * @date 2024-07-12 15:09
 * @description: 用户修改对象
 */
@Data
public class UserUpdateDTO {

    @NotNull(message = "ID不能为空")
    private Long id;

    /**
     * 用户名（登录名）
     */
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[\\S]{4,20}$", message = "用户名格式必须为4至20位的非空字符串")
    private String username;

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
    private LocalDateTime birthday;

    /**
     * 状态（0正常，1禁用）
     */
    @NotNull(message = "状态不能为空")
    @EnumValue(enumClass = BusinessStatusEnum.class, message = "状态只能为0（正常）或1（冻结）")
    private Integer status;
}