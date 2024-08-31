package com.halcyon.vo.file;

import com.halcyon.file.client.FileClientConfig;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-07-31 14:33
 * @description: 单个配置对象
 */
@Data
public class FileConfigSingleVO {

    /**
     * 编号
     */
    private Long id;

    /**
     * 配置名
     */
    private String name;

    /**
     * 存储器，参见 FileStorageEnum 枚举类
     */
    private Integer storage;

    /**
     * 是否为主配置
     */
    private Integer master;

    /**
     * 存储配置
     */
    private FileClientConfig config;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
