package com.halcyon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halcyon.dao.entity.SysRoleMenu;
import com.halcyon.dao.mapper.SysRoleMenuMapper;
import com.halcyon.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import static com.halcyon.utils.CollectionUtils.convertSet;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务实现类
 *
 * @author sjh
 * @since 2024-04-24 10:35:56
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

}

