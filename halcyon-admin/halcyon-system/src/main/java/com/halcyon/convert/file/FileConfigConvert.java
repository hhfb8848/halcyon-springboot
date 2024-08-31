package com.halcyon.convert.file;

import com.halcyon.dao.entity.SysFileConfig;
import com.halcyon.dto.file.FileConfigCreateDTO;
import com.halcyon.dto.file.FileConfigUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-07-30 18:54
 * @description: 文件配置转换
 */
@Mapper
public interface FileConfigConvert {

    FileConfigConvert INSTANCE = Mappers.getMapper(FileConfigConvert.class);

    /**
     * 转换创建对象
     * @param bean 源对象
     * @return 目标
     */
    @Mapping(target = "config", ignore = true)
    SysFileConfig convertCreateDTO(FileConfigCreateDTO bean);

    /**
     * 转换修改对象
     * @param bean 源对象
     * @return 目标
     */
    @Mapping(target = "config", ignore = true)
    SysFileConfig convertUpdateDTO(FileConfigUpdateDTO bean);
}
