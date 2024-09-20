package com.halcyon.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halcyon.annotation.AdminPrefix;
import com.halcyon.annotation.Log;
import com.halcyon.enums.OperBusinessType;
import com.halcyon.enums.StatusCodeEnum;
import com.halcyon.dao.entity.SysMenu;

import com.halcyon.dto.menu.MenuQueryDTO;
import com.halcyon.service.SysMenuService;

import com.halcyon.service.SysUserRoleService;
import com.halcyon.model.vo.ResponseResult;
import com.halcyon.vo.menu.SimpleMenuVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 菜单权限表(SysMenu)表控制层
 *
 * @author sjh
 * @since 2024-04-25 10:36:46
 */
@AdminPrefix
@RequestMapping("/sysMenu")
@AllArgsConstructor
public class SysMenuController {
    /**
     * 服务对象
     */
    private final SysMenuService sysMenuService;

    private final SysUserRoleService userRoleService;


    /**
     * 返回菜单列表，树由前端构建（菜单管理）
     *
     * @param queryDTO 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    @SaCheckPermission("system:menu:query")
    public ResponseResult<List<SysMenu>> listMenu(MenuQueryDTO queryDTO) {
        LambdaQueryWrapper<SysMenu> query = new LambdaQueryWrapper<>();
        query.orderByAsc(SysMenu::getSortOrder);
        return ResponseResult.ok(this.sysMenuService.list(query));
    }

    /**
     * 返回简单菜单列表，树由前端构建（用户菜单）
     *
     * @return 所有数据
     */
    @GetMapping("/listSimple")
    public ResponseResult<List<SimpleMenuVO>> listSimple() {
        List<SimpleMenuVO> simpleMenuVOS = this.sysMenuService.listSimpleMenu();
        return ResponseResult.ok(simpleMenuVOS);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    @SaCheckPermission("system:menu:detail")
    public ResponseResult<SysMenu> selectOne(@PathVariable Long id) {
        return ResponseResult.ok(this.sysMenuService.getById(id));
    }

    /**
     * 新增菜单
     *
     * @param sysMenu 实体对象
     * @return 新增结果
     */
    @Log(title = "菜单管理",businessType = OperBusinessType.INSERT)
    @PostMapping("/create")
    @SaCheckPermission("system:menu:create")
    public ResponseResult<Boolean> insert(@RequestBody SysMenu sysMenu) {
        sysMenu.setCreateBy(Long.valueOf(StpUtil.getLoginId().toString()));
        return ResponseResult.ok(this.sysMenuService.save(sysMenu));
    }

    /**
     * 修改数据
     *
     * @param sysMenu 实体对象
     * @return 修改结果
     */
    @Log(title = "菜单管理",businessType = OperBusinessType.UPDATE)
    @PutMapping("/update")
    @SaCheckPermission("system:menu:update")
    public ResponseResult<Void> update(@RequestBody SysMenu sysMenu) {
        if (Objects.nonNull(sysMenu.getParentId()) && sysMenu.getParentId().equals(sysMenu.getId())) {
            return ResponseResult.fail(StatusCodeEnum.VALID_ERROR.getCode(), "父菜单不能为本身");
        }
        sysMenu.setUpdateBy(Long.valueOf(StpUtil.getLoginId().toString()));
        this.sysMenuService.updateById(sysMenu);
        return ResponseResult.ok();
    }

    /**
     * 删除菜单
     *
     * @return 删除结果
     */
    @Log(title = "菜单管理",businessType = OperBusinessType.UPDATE)
    @DeleteMapping("/delete/{id}")
    @SaCheckPermission("system:menu:delete")
    public ResponseResult<Boolean> delete(@PathVariable Long id) {
        return ResponseResult.ok(this.sysMenuService.deleteById(id));
    }

}

