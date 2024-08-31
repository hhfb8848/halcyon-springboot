package com.halcyon.dao.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

/**
 * 文件内容表(SysFileContent)表实体类
 *
 * @author sjh
 * @since 2024-07-30 14:30:28
 */
@SuppressWarnings("serial")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysFileContent  {
    /**
    * 编号
    */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
    * 配置编号
    */    
    private Long configId;

    /**
    * 文件路径
    */    
    private String path;

    /**
    * 文件内容
    */    
    private byte[] content;

    /**
    * 创建者
    */    
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否删除 0否1是
     */
    @TableLogic
    private Integer delFlag;




}

