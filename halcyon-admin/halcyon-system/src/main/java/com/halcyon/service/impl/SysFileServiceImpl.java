package com.halcyon.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halcyon.exception.ServiceException;
import com.halcyon.file.client.FileClient;
import com.halcyon.file.client.s3.FilePreSignedUrlRespDTO;
import com.halcyon.utils.BeanCopyUtils;
import com.halcyon.utils.FileTypeUtils;
import com.halcyon.utils.FileUtils;
import com.halcyon.dao.entity.SysFile;
import com.halcyon.dao.mapper.SysFileMapper;
import com.halcyon.dto.file.FileQueryDTO;
import com.halcyon.service.SysFileConfigService;
import com.halcyon.service.SysFileService;
import com.halcyon.vo.file.FilePreSignedUrlVO;
import com.halcyon.vo.file.FileSimpleVO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 文件表(SysFile)表服务实现类
 *
 * @author sjh
 * @since 2024-07-30 14:30:26
 */
@Service
@RequiredArgsConstructor
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements SysFileService {

    private final SysFileMapper fileMapper;

    private final SysFileConfigService fileConfigService;

    @Override
    @SneakyThrows
    public String createFile(String name, String path, byte[] content) {
        // 计算默认的 path 名
        String type = FileTypeUtils.getMineType(content, name);
        if (StrUtil.isEmpty(path)) {
            path = FileUtils.generatePath(content, name);
        }
        // 如果 name 为空，则使用 path 填充
        if (StrUtil.isEmpty(name)) {
            name = path;
        }

        // 上传到文件存储器
        FileClient client = fileConfigService.getMasterFileClient();
        Assert.notNull(client, "客户端(master) 不能为空");
        String url = client.upload(content, path, type);

        // 保存到数据库
        SysFile file = new SysFile();
        file.setConfigId(client.getId());
        file.setName(name);
        file.setPath(path);
        file.setUrl(url);
        file.setType(type);
        file.setSize(content.length);
        file.setCreateBy(Long.valueOf(StpUtil.getLoginId().toString()));
        fileMapper.insert(file);
        return url;
    }

    @Override
    public Long createFile(SysFile sysFile) {
        fileMapper.insert(sysFile);
        return sysFile.getId();
    }

    @Override
    public void deleteFile(Long id) throws Exception {
        // 校验存在
        SysFile sysFile = fileMapper.selectById(id);
        if (Objects.isNull(sysFile)) {
            throw new ServiceException("文件不存在");
        }
        // 从文件存储器中删除
        FileClient client = fileConfigService.getFileClient(sysFile.getConfigId());
        Assert.notNull(client, "客户端({}) 不能为空", sysFile.getConfigId());
        client.delete(sysFile.getPath());
        // 删除记录
        fileMapper.deleteById(id);
    }

    @Override
    public byte[] getFileContent(Long configId, String path) throws Exception {
        FileClient client = fileConfigService.getFileClient(configId);
        Assert.notNull(client, "客户端({}) 不能为空", configId);
        return client.getContent(path);
    }

    @Override
    public FilePreSignedUrlVO getFilePreSignedUrl(String path) throws Exception {
        FileClient fileClient = fileConfigService.getMasterFileClient();
        FilePreSignedUrlRespDTO preSignedObjectUrl = fileClient.getPreSignedObjectUrl(path);
        FilePreSignedUrlVO filePreSignedUrlVO = BeanCopyUtils.copyBean(preSignedObjectUrl, FilePreSignedUrlVO.class);
        filePreSignedUrlVO.setConfigId(fileClient.getId());
        return filePreSignedUrlVO;
    }

    @Override
    public IPage<FileSimpleVO> pageFile(FileQueryDTO queryDTO) {
        Page<FileSimpleVO> page = new Page<>(queryDTO.getCurrent(), queryDTO.getSize());
        return fileMapper.selectFileVOPage(page, queryDTO);
    }
}

