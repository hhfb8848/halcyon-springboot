package com.halcyon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halcyon.dao.entity.SysLoginLog;
import com.halcyon.dao.mapper.SysLoginLogMapper;
import com.halcyon.service.SysLoginLogService;
import org.springframework.stereotype.Service;

/**
 * 系统访问记录(SysLoginLog)表服务实现类
 *
 * @author sjh
 * @since 2024-04-24 10:35:56
 */
@Service("sysLoginLogService")
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

}

