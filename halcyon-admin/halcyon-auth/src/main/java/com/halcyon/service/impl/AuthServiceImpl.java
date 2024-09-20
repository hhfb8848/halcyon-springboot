package com.halcyon.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halcyon.enums.BusinessStatusEnum;
import com.halcyon.enums.RoleEnum;
import com.halcyon.exception.ServiceException;
import com.halcyon.utils.AddressUtils;
import com.halcyon.utils.BeanCopyUtils;
import com.halcyon.utils.IpUtils;
import com.halcyon.utils.TimeUtils;
import com.halcyon.dao.entity.SysRole;
import com.halcyon.dao.entity.SysUser;
import com.halcyon.dao.entity.SysUserRole;
import com.halcyon.dao.mapper.SysRoleMapper;
import com.halcyon.dao.mapper.SysUserMapper;
import com.halcyon.dao.mapper.SysUserRoleMapper;
import com.halcyon.dto.UserRegisterDTO;
import com.halcyon.service.AuthService;
import com.halcyon.dto.UserLoginDTO;
import com.halcyon.service.PermissionService;
import com.halcyon.service.SysMenuService;
import com.halcyon.service.SysRoleService;
import com.halcyon.vo.UserLoginVO;
import com.halcyon.vo.menu.AsyncRoutesVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.halcyon.enums.StatusCodeEnum.*;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-05-09 10:40
 * @description: 账号相关实现
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final SysUserMapper userMapper;

    private final SysUserRoleMapper userRoleMapper;

    private final SysMenuService menuService;

    private final SysRoleMapper roleMapper;

    private final SysRoleService roleService;

    private final PermissionService permissionService;


    @Override
    public void register(UserRegisterDTO userRegisterDTO) {

//        SysUser exist = userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getEmail, userRegisterDTO.getEmail()));
//        if (Objects.nonNull(exist)){
//            throw new ServiceException(USER_EXIST);
//        }
//        String password = SaSecureUtil.md5(userRegisterDTO.getPassword());
//
//        SysUser sysUser = SysUser.builder()
//                .email(userRegisterDTO.getEmail())
//                .password(userRegisterDTO.getPassword())
//                .nickname(userRegisterDTO.getNickname()).build();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserLoginVO login(UserLoginDTO userLoginDTO) {
        SysUser sysUser = userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, userLoginDTO.getUsername()).or().eq(SysUser::getEmail, userLoginDTO.getUsername()));
        if (Objects.isNull(sysUser)) {
            throw new ServiceException(USER_NOT_EXIST);
        }
        if (sysUser.getStatus().equals(BusinessStatusEnum.DISABLED.getValue())) {
            throw new ServiceException(USER_FREEZE);
        }
        // 密码校验
        String password = SaSecureUtil.md5(userLoginDTO.getPassword());
        if (!sysUser.getPassword().equals(password)) {
            throw new ServiceException(LOGIN_ERROR);
        }
        // 查询用户角色
        List<SysUserRole> userRoleList = userRoleMapper.selectList(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, sysUser.getId()));
        Set<Long> roleIdList = userRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toSet());

        // 查看所属角色是否被禁用
        List<SysRole> sysRoles = roleMapper.selectList(new LambdaQueryWrapper<SysRole>().in(SysRole::getId, roleIdList));
        ArrayList<String> roleCodeList = new ArrayList<>();
        boolean existDisable = false;
        boolean hasSuperAdmin = false;
        for (SysRole role : sysRoles) {
            roleCodeList.add(role.getRoleCode());
            if (role.getStatus() == BusinessStatusEnum.DISABLED.getValue()) {
                existDisable = true;
            }
            if (role.getId().equals(RoleEnum.SUPERADMIN.getId())) {
                hasSuperAdmin = true;
            }
        }
        if (existDisable && !hasSuperAdmin) {
            throw new ServiceException(ROLE_FREEZE);
        }
        // 构建菜单树
        List<AsyncRoutesVO> asyncRoutesVOList = menuService.buildMenuTreeByRoles(roleIdList);

        if (asyncRoutesVOList.isEmpty()) {
            throw new ServiceException(USER_NO_ACCESS);
        }
        // 验证成功后的登录处理
        StpUtil.login(sysUser.getId());
        // 用户角色code与权限,用户名存入缓存
        SaSession currentSession = StpUtil.getTokenSession();
        currentSession.set(SaSession.USER,sysUser.getUsername());
        currentSession.set(SaSession.ROLE_LIST, roleCodeList);
        currentSession.set(SaSession.PERMISSION_LIST, permissionService.getMenuPermissionByRoles(roleIdList));

        // 获取当前回话的token
        String token = StpUtil.getTokenInfo().getTokenValue();
        UserLoginVO userLoginVO = BeanCopyUtils.copyBean(sysUser, UserLoginVO.class);
        userLoginVO.setRoles(roleCodeList);
        userLoginVO.setAccessToken(token);
        userLoginVO.setAsyncRoutesVOList(asyncRoutesVOList);
        return userLoginVO;
    }

    ;
}
