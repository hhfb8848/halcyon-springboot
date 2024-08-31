package com.halcyon.convert.sysConfig;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halcyon.dao.entity.SysConfig;
import com.halcyon.dto.sysConfig.SysConfigCreateDTO;
import com.halcyon.dto.sysConfig.SysConfigUpdateDTO;
import com.halcyon.vo.sysConfig.SysConfigSimpleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-08-02 15:14
 * @description: 配置转换
 */
@Mapper
public interface SysConfigConvert {

    SysConfigConvert INSTANCE = Mappers.getMapper(SysConfigConvert.class);

    Page<SysConfigSimpleVO> convertSimplePage(Page<SysConfig> page);

    SysConfig convertCreateDTO(SysConfigCreateDTO createDTO);

    SysConfig convertUpdateDTO(SysConfigUpdateDTO updateDTO);
}
