package com.halcyon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halcyon.dao.entity.SysLoginLog;
import com.halcyon.dao.entity.SysOperLog;
import com.halcyon.dao.mapper.SysLoginLogMapper;
import com.halcyon.event.LoginLogEvent;
import com.halcyon.event.OperLogEvent;
import com.halcyon.service.SysLoginLogService;
import com.halcyon.utils.AddressUtils;
import com.halcyon.utils.BeanCopyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 系统访问记录(SysLoginLog)表服务实现类
 *
 * @author sjh
 * @since 2024-04-24 10:35:56
 */
@Service
@RequiredArgsConstructor
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

    private final SysLoginLogMapper loginLogMapper;

    /**
     * 登录日志记录
     *
     */
    @Async
    @EventListener
    public void recordLogin(LoginLogEvent loginLogEvent) {
        SysLoginLog loginLog = BeanCopyUtils.copyBean(loginLogEvent,SysLoginLog.class);
        loginLogMapper.insert(loginLog);
    }
}

