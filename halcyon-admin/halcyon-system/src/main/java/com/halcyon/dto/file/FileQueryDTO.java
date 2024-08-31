package com.halcyon.dto.file;

import com.halcyon.model.dto.BasePageDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-07-31 18:03
 * @description: 文件查询对象
 */
@Data
public class FileQueryDTO extends BasePageDTO {

    private String type;

    /**
     * 第一个为开始时间，第二个为结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] uploadTimeArr;

}
