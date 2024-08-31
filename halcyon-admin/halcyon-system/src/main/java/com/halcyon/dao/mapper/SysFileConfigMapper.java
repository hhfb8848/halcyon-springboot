package com.halcyon.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.halcyon.dao.entity.SysFileConfig;
import com.halcyon.vo.file.FileConfigSimpleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 文件配置表(SysFileConfig)表数据库访问层
 *
 * @author sjh
 * @since 2024-07-30 14:30:28
 */
@Mapper
public interface SysFileConfigMapper extends BaseMapper<SysFileConfig> {

    default SysFileConfig selectByMaster() {
        return selectOne(new LambdaQueryWrapper<SysFileConfig>().eq(SysFileConfig::getMaster, true));
    }
    /**
     * 自定义分页查询
     * @param page 分页对象
     * @param query 查询对象
     * @return 查询结果
     */
    IPage<FileConfigSimpleVO> selectFileConfigVOPage(IPage<FileConfigSimpleVO> page, @Param("query") SysFileConfig query);
}

