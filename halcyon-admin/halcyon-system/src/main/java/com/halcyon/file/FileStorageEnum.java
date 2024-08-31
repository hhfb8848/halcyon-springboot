package com.halcyon.file;

import cn.hutool.core.util.ArrayUtil;
import com.halcyon.file.client.FileClient;
import com.halcyon.file.client.FileClientConfig;
import com.halcyon.file.client.db.DBFileClient;
import com.halcyon.file.client.db.DBFileClientConfig;
import com.halcyon.file.client.ftp.FtpFileClient;
import com.halcyon.file.client.ftp.FtpFileClientConfig;
import com.halcyon.file.client.local.LocalFileClient;
import com.halcyon.file.client.local.LocalFileClientConfig;
import com.halcyon.file.client.s3.S3FileClient;
import com.halcyon.file.client.s3.S3FileClientConfig;
import com.halcyon.file.client.sftp.SftpFileClient;
import com.halcyon.file.client.sftp.SftpFileClientConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件存储器枚举
 *
 * @author 芋道源码
 */
@AllArgsConstructor
@Getter
public enum FileStorageEnum {

    /**
     * 基于 DB 存储
     */
    DB(1, DBFileClientConfig.class, DBFileClient.class),

    /**
     * 基于本地
     */
    LOCAL(10, LocalFileClientConfig.class, LocalFileClient.class),

    /**
     * 基于FTP
     */
    FTP(11, FtpFileClientConfig.class, FtpFileClient.class),

    /**
     * 基于SFTP
     */
    SFTP(12, SftpFileClientConfig.class, SftpFileClient.class),

    /**
     * 基于S3
     */
    S3(20, S3FileClientConfig.class, S3FileClient.class),
    ;

    /**
     * 存储器
     */
    private final Integer storage;

    /**
     * 配置类
     */
    private final Class<? extends FileClientConfig> configClass;
    /**
     * 客户端类
     */
    private final Class<? extends FileClient> clientClass;

    public static FileStorageEnum getByStorage(Integer storage) {
        return ArrayUtil.firstMatch(o -> o.getStorage().equals(storage), values());
    }

}
