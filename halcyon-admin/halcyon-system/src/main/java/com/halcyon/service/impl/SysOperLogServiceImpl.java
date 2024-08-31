package com.halcyon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halcyon.dao.entity.SysOperLog;
import com.halcyon.dao.mapper.SysOperLogMapper;
import com.halcyon.service.SysOperLogService;
import org.springframework.stereotype.Service;

/**
 * 操作日志记录表(SysOperLog)表服务实现类
 *
 * @author sjh
 * @since 2024-04-24 10:35:56
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements SysOperLogService {

}

