package com.halcyon.dto.file;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-07-30 21:11
 * @description: 文件上传（模式二）
 */
@Data
public class FileCreateDTO {

    /**
     * 文件配置编号
     */
    @NotNull(message = "文件配置编号不能为空")
    private Long configId;

    /**
     * 文件路径
     */
    @NotNull(message = "文件路径不能为空")
    private String path;

    /**
     * 原文件名
     */
    @NotNull(message = "原文件名不能为空")
    private String name;

    /**
     * 文件 URL
     */
    @NotNull(message = "文件 URL不能为空")
    private String url;

    /**
     * 文件 MIME 类型，如：application/octet-stream
     */
    private String type;

    /**
     * 文件大小
     */
    private Integer size;
}
