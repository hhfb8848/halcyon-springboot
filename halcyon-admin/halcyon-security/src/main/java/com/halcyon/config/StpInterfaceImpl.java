package com.halcyon.config;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-05-07 15:23
 * @description: 自定义权限加载接口实现类
 */
@Component
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        Object data = StpUtil.getTokenSession().get(SaSession.PERMISSION_LIST);
        if (data instanceof HashSet) {
            HashSet<String> hashSet = (HashSet<String>) data;
            return new ArrayList<String>(hashSet);
        }
        return new ArrayList<String>();
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Object data = StpUtil.getTokenSession().get(SaSession.ROLE_LIST);
        if (data instanceof ArrayList) {
            return (ArrayList<String>) data;
        }
        return new ArrayList<String>();
    }
}
