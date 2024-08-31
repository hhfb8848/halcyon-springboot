package com.halcyon.dto.sysConfig;

import com.halcyon.model.dto.BasePageDTO;
import lombok.Data;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-08-02 15:04
 * @description: 配置查询对象
 */
@Data
public class SysConfigQueryDTO extends BasePageDTO {

    private String configName;
}
