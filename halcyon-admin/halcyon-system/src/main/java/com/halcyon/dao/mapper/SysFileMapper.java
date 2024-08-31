package com.halcyon.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.halcyon.dao.entity.SysFile;
import com.halcyon.dto.file.FileQueryDTO;
import com.halcyon.vo.file.FileSimpleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 文件表(SysFile)表数据库访问层
 *
 * @author sjh
 * @since 2024-07-30 14:30:19
 */
@Mapper
public interface SysFileMapper extends BaseMapper<SysFile> {

    /**
     * 自定义分页
     */
    IPage<FileSimpleVO> selectFileVOPage(IPage<FileSimpleVO> page, @Param("query") FileQueryDTO query);
}

