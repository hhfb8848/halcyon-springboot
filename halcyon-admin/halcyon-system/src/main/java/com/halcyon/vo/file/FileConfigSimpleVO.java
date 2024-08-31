package com.halcyon.vo.file;

import lombok.Data;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-07-31 0:49
 * @description: 文件配置简单对象
 */
@Data
public class FileConfigSimpleVO {

    private Long id;

    /**
     * 配置名
     */
    private String name;

    /**
     * 存储器
     */
    private Integer storage;

    /**
     * 备注
     */
    private String remark;

    private Integer master;

    private Long createBy;
}
