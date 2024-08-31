package com.halcyon.vo.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-07-30 21:08
 * @description: 文件预签名
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilePreSignedUrlVO {

    /**
     * 配置编号
     */
    private Long configId;

    /**
     * 文件上传 URL
     */
    private String uploadUrl;

    /**
     * 为什么要返回 url 字段？
     *
     * 前端上传完文件后，需要使用该 URL 进行访问
     */
    private String url;
}
