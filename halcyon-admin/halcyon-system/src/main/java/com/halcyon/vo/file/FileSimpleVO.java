package com.halcyon.vo.file;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-07-31 17:57
 * @description: 文件简单对象
 */
@Data
public class FileSimpleVO {

    /**
     * 文件编号
     */
    private Long id;

    /**
     * 配置编号
     */
    private Long configId;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件 URL
     */
    private String url;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件大小
     */
    private Integer size;


    /**
     * 创建时间
     */

    private LocalDateTime createTime;



}
