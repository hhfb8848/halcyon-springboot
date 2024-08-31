package com.halcyon.dao.entity;

import java.time.LocalDateTime;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.halcyon.file.client.FileClientConfig;
import com.halcyon.file.client.db.DBFileClientConfig;
import com.halcyon.file.client.ftp.FtpFileClientConfig;
import com.halcyon.file.client.local.LocalFileClientConfig;
import com.halcyon.file.client.s3.S3FileClientConfig;
import com.halcyon.file.client.sftp.SftpFileClientConfig;
import com.halcyon.utils.JsonUtils;
import lombok.*;

/**
 * 文件配置表(SysFileConfig)表实体类
 *
 * @author sjh
 * @since 2024-07-30 14:30:28
 */
@TableName(value = "sys_file_config", autoResultMap = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode()
public class SysFileConfig  {
    /**
    * 编号
    */
    @TableId
    private Long id;

    /**
    * 配置名
    */    
    private String name;

    /**
    * 存储器
    */    
    private Integer storage;

    /**
    * 备注
    */    
    private String remark;

    /**
    * 是否为主配置
    */    
    private Integer master;

    /**
     * 配置
     */
    @TableField(typeHandler = FileClientConfigTypeHandler.class)
    private FileClientConfig config;

    public static class FileClientConfigTypeHandler extends AbstractJsonTypeHandler<Object> {

        @Override
        protected Object parse(String json) {
            FileClientConfig config = JsonUtils.parseObjectQuietly(json, new TypeReference<FileClientConfig>() {});
            if (config != null) {
                return config;
            }

            // 兼容老版本的包路径
            String className = JsonUtils.parseObject(json, "@class", String.class);
            className = StrUtil.subAfter(className, ".", true);
            switch (className) {
                case "DBFileClientConfig":
                    return JsonUtils.parseObject2(json, DBFileClientConfig.class);
                case "FtpFileClientConfig":
                    return JsonUtils.parseObject2(json, FtpFileClientConfig.class);
                case "LocalFileClientConfig":
                    return JsonUtils.parseObject2(json, LocalFileClientConfig.class);
                case "SftpFileClientConfig":
                    return JsonUtils.parseObject2(json, SftpFileClientConfig.class);
                case "S3FileClientConfig":
                    return JsonUtils.parseObject2(json, S3FileClientConfig.class);
                default:
                    throw new IllegalArgumentException("未知的 FileClientConfig 类型：" + json);
            }
        }

        @Override
        protected String toJson(Object obj) {
            return JsonUtils.toJsonString(obj);
        }

    }


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

