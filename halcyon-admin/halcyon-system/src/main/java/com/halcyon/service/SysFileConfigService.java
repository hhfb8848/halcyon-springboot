package com.halcyon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.halcyon.file.client.FileClient;
import com.halcyon.dao.entity.SysFileConfig;
import com.halcyon.dto.file.FileConfigQueryDTO;
import com.halcyon.vo.file.FileConfigSimpleVO;

/**
 * 文件配置表(SysFileConfig)表服务接口
 *
 * @author sjh
 * @since 2024-07-30 14:30:28
 */
public interface SysFileConfigService extends IService<SysFileConfig> {



    /**
     * 创建文件配置
     * @param fileConfig 配置
     * @return ID
     */
    Long createFileConfig(SysFileConfig fileConfig);

    /**
     * 修改文件配置
     * @param sysFileConfig 配置
     * @return ID
     */
    Long updateFileConfig(SysFileConfig sysFileConfig);

    /**
     * 更新文件配置为 Master
     *
     * @param id 编号
     */
    void updateFileConfigMaster(Long id);

    /**
     * 获得 Master 文件客户端
     *
     * @return 文件客户端
     */
    FileClient getMasterFileClient();

    /**
     * 获得指定编号的文件客户端
     *
     * @param id 配置编号
     * @return 文件客户端
     */
    FileClient getFileClient(Long id);

    /**
     * 配置查询分页
     * @param queryDTO 查询对象
     * @return FileConfigVO
     */
    IPage<FileConfigSimpleVO> pageFileConfigVO(FileConfigQueryDTO queryDTO);

    /**
     * 获取详细配置
     * @param id ID
     * @return 配置
     */
    SysFileConfig getFileConfig(Long id);

    /**
     * 逻辑删除配置
     * @param id
     */
    void deleteFileConfig(Long id);
}

