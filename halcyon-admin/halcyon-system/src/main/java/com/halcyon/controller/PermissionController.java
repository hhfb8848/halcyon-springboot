package com.halcyon.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.halcyon.annotation.AdminPrefix;
import com.halcyon.annotation.Log;
import com.halcyon.dto.roleMenu.RoleMenuAssignDTO;
import com.halcyon.dto.userRole.UserRoleAssignDTO;
import com.halcyon.enums.OperBusinessType;
import com.halcyon.service.PermissionService;
import com.halcyon.model.vo.ResponseResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-08-01 20:38
 * @description: 权限相关
 */
@AdminPrefix
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/permission")
public class PermissionController {

    private final PermissionService permissionService;
    /**
     * 获取角色已分配菜单
     *
     * @return 所有数据
     */

    @GetMapping("/getMenuIdList/{roleId}")
    @SaCheckPermission("system:permission:getMenus")
    public ResponseResult<List<Long>> getMenuIdList(@PathVariable Long roleId) {
        return ResponseResult.ok( permissionService.getMenuIdListByRole(roleId));
    }

    /**
     * 为角色分配菜单
     */
    @Log(title = "权限分配",businessType = OperBusinessType.GRANT)
    @PostMapping("/assignForRole")
    @SaCheckPermission("system:permission:assignMenu")
    public ResponseResult<Void> assignForRole(@Valid @RequestBody RoleMenuAssignDTO assignDTO){
        permissionService.assignMenu(assignDTO.getRoleId(),assignDTO.getMenuIds());
        return ResponseResult.ok();
    }

    /**
     * 获取用户所分配角色
     *
     * @return 所有数据
     */
    @GetMapping("/getRoleIds/{userId}")
    @SaCheckPermission("system:permission:getRoles")
    public ResponseResult<List<Long>> getRoleIds(@PathVariable Long userId) {
        return ResponseResult.ok( permissionService.getRoleIdsByUser(userId));
    }

    /**
     * 为用户分配角色
     */
    @Log(title = "权限分配",businessType = OperBusinessType.GRANT)
    @PostMapping("/assignRoleForUser")
    @SaCheckPermission("system:permission:assignRole")
    public ResponseResult<Void> assignRoleForUser(@Valid @RequestBody UserRoleAssignDTO assignDTO){
        permissionService.assignRole(assignDTO.getUserId(),assignDTO.getRoleIds());
        return ResponseResult.ok();
    }
}
