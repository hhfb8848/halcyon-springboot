package com.halcyon.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halcyon.annotation.AdminPrefix;
import com.halcyon.annotation.RateLimiter;
import com.halcyon.enums.BooleanEnum;
import com.halcyon.enums.StatusCodeEnum;
import com.halcyon.dao.entity.SysMenu;

import com.halcyon.dto.menu.MenuQueryDTO;
import com.halcyon.service.SysMenuService;

import com.halcyon.service.SysUserRoleService;
import com.halcyon.vo.menu.AsyncRoutesMetaVO;
import com.halcyon.vo.menu.AsyncRoutesVO;
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
     * 根据登录角色加载菜单树(用不到)
     *
     */
    @GetMapping("/roleMenuTree")
    @RateLimiter
    public ResponseResult<List<AsyncRoutesVO>> selectRoleMenuTree() {
        return ResponseResult.ok(buildMenuTree(this.sysMenuService.list(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getVisible,0))));
    }

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
     * 新增数据
     *
     * @param sysMenu 实体对象
     * @return 新增结果
     */
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
    @DeleteMapping("/delete/{id}")
    @SaCheckPermission("system:menu:delete")
    public ResponseResult<Boolean> delete(@PathVariable Long id) {
        return ResponseResult.ok(this.sysMenuService.deleteById(id));
    }

    /**
     * 构建菜单树
     */
    private List<AsyncRoutesVO> buildMenuTree(List<SysMenu> menuList) {
        List<AsyncRoutesVO> rootNodes = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                rootNodes.add(buildMenuNode(menu, menuList));
            }
        }
        // 对根节点进行排序
        rootNodes.sort(Comparator.comparingInt(o -> o.getMeta().getSortOrder()));
        return rootNodes;
    }

    private AsyncRoutesVO buildMenuNode(SysMenu menu, List<SysMenu> menuList) {
        AsyncRoutesVO node = new AsyncRoutesVO();
        node.setPath(menu.getPath());
        node.setName(menu.getName());
        node.setComponent(menu.getComponent());
        node.setRedirect(menu.getRedirect());
        node.setType(menu.getType());

        // 设置路由元信息
        AsyncRoutesMetaVO meta = new AsyncRoutesMetaVO();
        meta.setTitle(menu.getTitle());
        meta.setIcon(menu.getIcon());
        meta.setRoles(StpUtil.getRoleList());
        meta.setSortOrder(menu.getSortOrder());
        meta.setKeepAlive(BooleanEnum.fromValue(menu.getCacheFlag()));
        meta.setFrameLoading(BooleanEnum.fromValue(menu.getFrameLoading()));
        meta.setAuths(Optional.ofNullable(menu.getPerms())
                .map(List::of)
                .orElse(Collections.emptyList()));
        meta.setFrameSrc(menu.getFrameSrc());
        node.setMeta(meta);
        // 递归构建子节点
        List<AsyncRoutesVO> children = new ArrayList<>();
        for (SysMenu childMenu : menuList) {
            if (childMenu.getParentId() != null && childMenu.getParentId().equals(menu.getId())) {
                children.add(buildMenuNode(childMenu, menuList));
            }
        }
        children.sort(Comparator.comparingInt(o -> o.getMeta().getSortOrder()));
        node.setChildren(children);
        return node;
    }
}

