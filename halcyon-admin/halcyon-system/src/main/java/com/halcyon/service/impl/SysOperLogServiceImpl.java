package com.halcyon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halcyon.dao.entity.SysOperLog;
import com.halcyon.dao.mapper.SysOperLogMapper;
import com.halcyon.event.OperLogEvent;
import com.halcyon.service.SysOperLogService;
import com.halcyon.utils.AddressUtils;
import com.halcyon.utils.BeanCopyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 操作日志记录表(SysOperLog)表服务实现类
 *
 * @author sjh
 * @since 2024-04-24 10:35:56
 */
@Service
@RequiredArgsConstructor
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements SysOperLogService {


    private final SysOperLogMapper operLogMapper;


    /**
     * 操作日志记录
     *
     * @param operLogEvent 操作日志事件
     */
    @Async
    @EventListener
    public void recordOper(OperLogEvent operLogEvent) {
        SysOperLog operLog = BeanCopyUtils.copyBean(operLogEvent,SysOperLog.class);
        // 远程查询操作地点
        operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
        operLogMapper.insert(operLog);
    }
}

