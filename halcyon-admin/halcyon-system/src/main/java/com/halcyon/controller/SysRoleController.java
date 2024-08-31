package com.halcyon.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.halcyon.annotation.AdminPrefix;
import com.halcyon.convert.role.RoleConvert;
import com.halcyon.enums.RoleEnum;
import com.halcyon.enums.StatusCodeEnum;
import com.halcyon.exception.ServiceException;
import com.halcyon.utils.BeanCopyUtils;
import com.halcyon.utils.StringUtils;
import com.halcyon.dao.entity.SysRole;
import com.halcyon.dao.entity.SysUserRole;
import com.halcyon.dto.role.RoleQueryDTO;
import com.halcyon.dto.role.SysRoleCreateDTO;
import com.halcyon.dto.role.SysRoleUpdateDTO;
import com.halcyon.service.SysRoleService;
import com.halcyon.service.SysUserRoleService;
import com.halcyon.model.vo.ResponseResult;
import com.halcyon.vo.role.RoleSimpleVO;
import com.halcyon.vo.role.SysRoleVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 角色信息表(SysRole)表控制层
 *
 * @author sjh
 * @since 2024-04-25 10:36:46
 */
@AdminPrefix
@RequestMapping("/sysRole")
@RequiredArgsConstructor
public class SysRoleController {
    /**
     * 服务对象
     */

    private final SysRoleService sysRoleService;

    private final SysUserRoleService sysUserRoleService;

    /**
     * 查询角色列表
     *
     * @param queryDTO 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    @SaCheckPermission("system:role:query")
    public ResponseResult<IPage<SysRoleVO>> selectPage(RoleQueryDTO queryDTO) {
        return ResponseResult.ok(this.sysRoleService.listRolePage(queryDTO));
    }

    /**
     * 角色详情
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    public ResponseResult<SysRole> selectOne(@PathVariable Long id) {
        return ResponseResult.ok(this.sysRoleService.getById(id));
    }

    /**
     * 新增角色
     *
     * @param createDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/create")
    @SaCheckPermission("system:role:create")
    public ResponseResult<Long> insert(@Valid @RequestBody SysRoleCreateDTO createDTO) {
        SysRole sysRole = BeanCopyUtils.copyBean(createDTO, SysRole.class);
        return ResponseResult.ok(this.sysRoleService.createRole(sysRole));
    }

    /**
     * 修改角色
     *
     * @param updateDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @SaCheckPermission("system:role:update")
    public ResponseResult<Long> update(@Valid @RequestBody SysRoleUpdateDTO updateDTO) {
        SysRole sysRole = BeanCopyUtils.copyBean(updateDTO, SysRole.class);
        return ResponseResult.ok(this.sysRoleService.updateRole(sysRole));
    }

    /**
     * 删除角色
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @SaCheckPermission("system:role:delete")
    public ResponseResult<Boolean> delete(@PathVariable Long id) {

        if (Objects.nonNull(RoleEnum.fromValue(id))) {
            throw new ServiceException(StatusCodeEnum.FAIL.getCode(), "系统内置角色无法删除");
        }
        long count = sysUserRoleService.count(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getRoleId, id));
        if (count > 0) {
            throw new ServiceException(StatusCodeEnum.FAIL.getCode(), "该角色已绑定用户，无法删除");
        }
        return ResponseResult.ok(this.sysRoleService.removeById(id));
    }

    /**
     * 查询所有简单角色数据
     *
     * @param queryDTO 查询实体
     * @return 所有数据
     */
    @GetMapping("/listAll")
    @SaCheckPermission("system:role:listSimpleAll")
    public ResponseResult<List<RoleSimpleVO>> selectAll(RoleQueryDTO queryDTO) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<SysRole>().like(StringUtils.isNotEmpty(queryDTO.getRoleName()), SysRole::getRoleName, queryDTO.getRoleName());
        List<SysRole> list = sysRoleService.list(queryWrapper);
        return ResponseResult.ok(RoleConvert.INSTANCE.convertSimpleList(list));
    }
}

