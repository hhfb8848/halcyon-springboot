package com.halcyon.dto.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halcyon.dao.entity.SysDictData;
import com.halcyon.dao.entity.SysLoginLog;
import com.halcyon.model.dto.BasePageDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-09-13 11:29
 * @description: 登录日志查询对象
 */
@Data
public class LoginLogQueryDTO extends Page<SysLoginLog> {

    private String account;

    private Integer status;

    /**
     * 第一个为开始时间，第二个为结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] loginTimeArr;
}
