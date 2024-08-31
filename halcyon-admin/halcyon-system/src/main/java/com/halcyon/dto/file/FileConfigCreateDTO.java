package com.halcyon.dto.file;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-07-30 18:26
 * @description: 文件配置创建DTO
 */
@Data
public class FileConfigCreateDTO {

    /**
     * 配置名
     */
    @NotNull(message = "配置名不能为空")
    private String name;

    /**
     * 存储器类型
     */
    @NotNull(message = "存储器不能为空")
    private Integer storage;

    /**
     * 存储配置
     */
    @NotNull(message = "存储配置不能为空")
    private Map<String, Object> config;

    /**
     * 备注
     */
    private String remark;

}
