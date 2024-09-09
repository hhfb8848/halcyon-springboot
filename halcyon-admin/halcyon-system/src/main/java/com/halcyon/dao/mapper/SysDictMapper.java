package com.halcyon.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.halcyon.dao.entity.SysDict;
import com.halcyon.vo.dict.DictAndDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典表(SysDict)表数据库访问层
 *
 * @author sjh
 * @since 2024-04-24 10:35:49
 */
@Repository
public interface SysDictMapper extends BaseMapper<SysDict> {
    /**
     * 获取全部字典以及字典下的数据项
     */
    List<DictAndDataVO> listAllDictAndData();
}

