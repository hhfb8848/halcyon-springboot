package com.halcyon.dto.file;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-07-30 20:36
 * @description: 文件上传对象
 */
@Data
public class FileUploadDTO {

    /**
     * 文件附件
     */
    @NotNull(message = "文件附件不能为空")
    private MultipartFile file;

    /**
     * 路径
     */
    private String path;
}
