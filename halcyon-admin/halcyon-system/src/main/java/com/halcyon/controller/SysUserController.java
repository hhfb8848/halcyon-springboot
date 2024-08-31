package com.halcyon.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.DesensitizedUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.halcyon.annotation.AdminPrefix;
import com.halcyon.annotation.RepeatSubmit;
import com.halcyon.utils.BeanCopyUtils;
import com.halcyon.dao.entity.SysUser;
import com.halcyon.dto.user.*;
import com.halcyon.service.SysUserService;
import com.halcyon.model.vo.ResponseResult;
import com.halcyon.vo.user.SysUserVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户表(SysUser)表控制层
 *
 * @author sjh
 * @since 2024-04-25 10:36:46
 */
@AdminPrefix
@RequestMapping("/sysUser")
@RequiredArgsConstructor
public class SysUserController {
    /**
     * 服务对象
     */
    private final SysUserService sysUserService;

    /**
     * 用户列表
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @SaCheckPermission("system:user:query")
    public ResponseResult<IPage<SysUserVO>> selectAll(UserQueryDTO queryDTO) {
        IPage<SysUserVO> sysUserVOPage = this.sysUserService.pageUserVO(queryDTO);
        // 脱敏
        List<SysUserVO> collect = sysUserVOPage.getRecords().stream().peek(sysUserVO -> {
            Optional.ofNullable(sysUserVO.getEmail()).ifPresent(email -> sysUserVO.setEmail(DesensitizedUtil.email(email)));
            Optional.ofNullable(sysUserVO.getPhone()).ifPresent(phone -> sysUserVO.setPhone(DesensitizedUtil.mobilePhone(phone)));
        }).collect(Collectors.toList());
        sysUserVOPage.setRecords(collect);
        return ResponseResult.ok(sysUserVOPage);
    }

    /**
     * 用户详情
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/getDetail/{id}")
    @SaCheckPermission("system:user:detail")
    public ResponseResult<SysUserVO> selectOne(@PathVariable Long id) {
        SysUser user = this.sysUserService.getById(id);
        return ResponseResult.ok(BeanCopyUtils.copyBean(user, SysUserVO.class));
    }

    /**
     * 创建新用户
     *
     * @param createDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/create")
    @RepeatSubmit
    @SaCheckPermission("system:user:create")
    public ResponseResult<Long> insert(@Valid @RequestBody SysUserCreateDTO createDTO) {
        SysUser sysUser = BeanCopyUtils.copyBean(createDTO, SysUser.class);
        sysUser.setCreateBy(Long.valueOf(StpUtil.getLoginId().toString()));
        return ResponseResult.ok(this.sysUserService.createUser(sysUser));
    }

    /**
     * 修改用户
     *
     * @param updateDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @SaCheckPermission("system:user:update")
    public ResponseResult<Long> update(@Valid @RequestBody UserUpdateDTO updateDTO) {
        SysUser sysUser = BeanCopyUtils.copyBean(updateDTO, SysUser.class);
        sysUser.setUpdateBy(Long.valueOf(StpUtil.getLoginId().toString()));
        return ResponseResult.ok(this.sysUserService.updateUser(sysUser));
    }

    /**
     * 修改用户状态
     *
     * @param updateDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/updateStatus")
    @SaCheckPermission("system:user:updateStatus")
    public ResponseResult<Long> updateStatus(@Valid @RequestBody UserUpdateStatusDTO updateDTO) {
        SysUser sysUser = BeanCopyUtils.copyBean(updateDTO, SysUser.class);
        sysUser.setUpdateBy(Long.valueOf(StpUtil.getLoginId().toString()));
        return ResponseResult.ok(this.sysUserService.updateUser(sysUser));
    }

    /**
     * 删除用户
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @SaCheckPermission("system:user:delete")
    public ResponseResult<Integer> delete(@RequestBody List<Long> idList) {
        return ResponseResult.ok(this.sysUserService.deleteByIds(idList));
    }

    /**
     * 重置密码
     *
     * @param updateDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/resetPassword")
    @SaCheckPermission("system:user:resetPwd")
    public ResponseResult<Boolean> resetPassword(@Valid @RequestBody UserResetPwdDTO updateDTO) {
        SysUser sysUser = BeanCopyUtils.copyBean(updateDTO, SysUser.class);
        sysUser.setUpdateBy(Long.valueOf(StpUtil.getLoginId().toString()));
        this.sysUserService.resetPassword(sysUser);
        return ResponseResult.ok(true);
    }
}

