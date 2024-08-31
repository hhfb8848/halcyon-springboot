package com.halcyon.file.client.db;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.halcyon.file.client.AbstractFileClient;
import com.halcyon.dao.entity.SysFileContent;
import com.halcyon.dao.mapper.SysFileContentMapper;

import java.util.Comparator;
import java.util.List;

/**
 * 基于 DB 存储的文件客户端的配置类
 *
 * @author 芋道源码
 */
public class DBFileClient extends AbstractFileClient<DBFileClientConfig> {

    private SysFileContentMapper fileContentMapper;

    public DBFileClient(Long id, DBFileClientConfig config) {
        super(id, config);
    }

    @Override
    protected void doInit() {
        fileContentMapper = SpringUtil.getBean(SysFileContentMapper.class);
    }

    @Override
    public String upload(byte[] content, String path, String type) {
        SysFileContent contentDO = new SysFileContent();
        contentDO.setConfigId(getId());
        contentDO.setPath(path);
        contentDO.setContent(content);
        fileContentMapper.insert(contentDO);
        // 拼接返回路径
        return super.formatFileUrl(config.getDomain(), path);
    }

    @Override
    public void delete(String path) {
        fileContentMapper.deleteByConfigIdAndPath(getId(), path);
    }

    @Override
    public byte[] getContent(String path) {
        List<SysFileContent> list = fileContentMapper.selectListByConfigIdAndPath(getId(), path);
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        // 排序后，拿 id 最大的，即最后上传的
        list.sort(Comparator.comparing(SysFileContent::getId));
        return CollUtil.getLast(list).getContent();
    }

}
