package com.halcyon.dto.file;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halcyon.dao.entity.SysFileConfig;
import lombok.Data;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-07-31 0:44
 * @description: 文件配置查询对象
 */
@Data
public class FileConfigQueryDTO extends Page<SysFileConfig> {

    private String name;
}
