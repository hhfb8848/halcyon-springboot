package com.halcyon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.halcyon.dao.entity.SysFile;
import com.halcyon.dto.file.FileQueryDTO;
import com.halcyon.vo.file.FilePreSignedUrlVO;
import com.halcyon.vo.file.FileSimpleVO;

/**
 * 文件表(SysFile)表服务接口
 *
 * @author sjh
 * @since 2024-07-30 14:30:24
 */
public interface SysFileService extends IService<SysFile> {

    /**
     * 保存文件，并返回文件的访问路径
     *
     * @param name    文件名称
     * @param path    文件路径
     * @param content 文件内容
     * @return 文件路径
     */
    String createFile(String name, String path, byte[] content);

    /**
     * 创建文件
     *
     * @param sysFile 创建信息
     * @return 编号
     */
    Long createFile(SysFile sysFile);

    /**
     * 删除文件
     *
     * @param id 编号
     */
    void deleteFile(Long id) throws Exception;

    /**
     * 获得文件内容
     *
     * @param configId 配置编号
     * @param path     文件路径
     * @return 文件内容
     */
    byte[] getFileContent(Long configId, String path) throws Exception;

    /**
     * 生成文件预签名地址信息
     *
     * @param path 文件路径
     * @return 预签名地址信息
     */
    FilePreSignedUrlVO getFilePreSignedUrl(String path) throws Exception;

    /**
     * 分页查询
     */
    IPage<FileSimpleVO> pageFile(FileQueryDTO queryDTO);

}

