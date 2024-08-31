package com.halcyon.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.halcyon.dao.entity.SysDict;
import org.springframework.stereotype.Repository;

/**
 * 字典表(SysDict)表数据库访问层
 *
 * @author sjh
 * @since 2024-04-24 10:35:49
 */
@Repository
public interface SysDictMapper extends BaseMapper<SysDict> {
}

