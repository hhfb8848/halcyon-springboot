package com.halcyon.dao.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 文件表(SysFile)表实体类
 *
 * @author sjh
 * @since 2024-07-30 14:30:22
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysFile  {
    /**
    * 文件编号
    */
    @TableId
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

