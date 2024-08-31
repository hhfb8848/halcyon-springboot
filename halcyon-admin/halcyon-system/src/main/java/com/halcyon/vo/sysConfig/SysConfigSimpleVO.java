package com.halcyon.vo.sysConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-08-02 15:02
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysConfigSimpleVO {

    /**
     * 参数主键
     */
    private Integer id;

    /**
     * 是否系统内置（0否1是）
     */
    private Integer type;

    /**
     * 参数名称
     */
    private String configName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    /**
     * 备注
     */
    private String remark;

}
