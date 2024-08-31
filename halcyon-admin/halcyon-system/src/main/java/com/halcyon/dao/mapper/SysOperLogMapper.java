package com.halcyon.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.halcyon.dao.entity.SysOperLog;
import org.springframework.stereotype.Repository;

/**
 * 操作日志记录表(SysOperLog)表数据库访问层
 *
 * @author sjh
 * @since 2024-04-24 10:35:56
 */
@Repository
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {

}

