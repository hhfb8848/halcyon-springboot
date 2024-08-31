package com.halcyon.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.halcyon.dao.entity.SysFileContent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 文件内容表(SysFileContent)表数据库访问层
 *
 * @author sjh
 * @since 2024-07-30 14:30:28
 */
@Mapper
public interface SysFileContentMapper extends BaseMapper<SysFileContent> {
    default void deleteByConfigIdAndPath(Long configId, String path) {
        this.delete(new LambdaQueryWrapper<SysFileContent>()
                .eq(SysFileContent::getConfigId, configId)
                .eq(SysFileContent::getPath, path));
    }

    default List<SysFileContent> selectListByConfigIdAndPath(Long configId, String path) {
        return selectList(new LambdaQueryWrapper<SysFileContent>()
                .eq(SysFileContent::getConfigId, configId)
                .eq(SysFileContent::getPath, path));
    }
}

