package com.halcyon.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.halcyon.enums.BooleanEnum;
import com.halcyon.enums.StatusCodeEnum;
import com.halcyon.exception.ServiceException;
import com.halcyon.file.client.FileClient;
import com.halcyon.file.client.FileClientFactory;
import com.halcyon.utils.BeanCopyUtils;
import com.halcyon.dao.entity.SysFileConfig;
import com.halcyon.dao.mapper.SysFileConfigMapper;
import com.halcyon.dto.file.FileConfigQueryDTO;
import com.halcyon.service.SysFileConfigService;
import com.halcyon.vo.file.FileConfigSimpleVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.Objects;

import static com.halcyon.utils.CacheUtils.buildAsyncReloadingCache;

/**
 * 文件配置表(SysFileConfig)表服务实现类
 *
 * @author sjh
 * @since 2024-07-30 14:30:28
 */
@Service
@RequiredArgsConstructor
@Validated
public class SysFileConfigServiceImpl extends ServiceImpl<SysFileConfigMapper, SysFileConfig> implements SysFileConfigService {

    private static final Long CACHE_MASTER_ID = 0L;

    /**
     * {@link FileClient} 缓存，通过它异步刷新 fileClientFactory
     */
    @Getter
    private final LoadingCache<Long, FileClient> clientCache = buildAsyncReloadingCache(Duration.ofSeconds(10L),
            new CacheLoader<Long, FileClient>() {

                @Override
                public FileClient load(Long id) {
                    SysFileConfig config = Objects.equals(CACHE_MASTER_ID, id) ?
                            fileConfigMapper.selectByMaster() : fileConfigMapper.selectById(id);
                    if (config != null) {
                        fileClientFactory.createOrUpdateFileClient(config.getId(), config.getStorage(), config.getConfig());
                    }
                    return fileClientFactory.getFileClient(null == config ? id : config.getId());
                }

            });

    private final FileClientFactory fileClientFactory;

    private final SysFileConfigMapper fileConfigMapper;

    @Override
    public Long createFileConfig(SysFileConfig fileConfig) {

        validateNameUniqueness(null,fileConfig.getName());
        // 默认非 master
        fileConfig.setMaster(BooleanEnum.FALSE.getValue());
        fileConfig.setCreateBy(Long.valueOf(StpUtil.getLoginId().toString()));
        fileConfigMapper.insert(fileConfig);
        return fileConfig.getId();
    }

    @Override
    public Long updateFileConfig(SysFileConfig sysFileConfig) {

        validateFileConfigExists(sysFileConfig.getId());

        validateNameUniqueness(sysFileConfig.getId(),sysFileConfig.getName());
        fileConfigMapper.updateById(sysFileConfig);
        // 清空缓存
        clearCache(sysFileConfig.getId(), null);
        return sysFileConfig.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFileConfigMaster(Long id) {
        // 校验存在
        validateFileConfigExists(id);
        // 更新其它为非 master
        SysFileConfig sysFileConfig = new SysFileConfig();
        sysFileConfig.setMaster(BooleanEnum.FALSE.getValue());
        fileConfigMapper.update(sysFileConfig,new QueryWrapper<>());
        // 更新
        fileConfigMapper.updateById(SysFileConfig.builder().id(id).master(BooleanEnum.TRUE.getValue()).build());

        // 清空缓存
        clearCache(null, BooleanEnum.TRUE.getValue());
    }

    @Override
    public FileClient getMasterFileClient() {
        return clientCache.getUnchecked(CACHE_MASTER_ID);
    }

    @Override
    public FileClient getFileClient(Long id) {
        return clientCache.getUnchecked(id);
    }

    @Override
    public IPage<FileConfigSimpleVO> pageFileConfigVO(FileConfigQueryDTO queryDTO) {
        Page<FileConfigSimpleVO> page = new Page<>(queryDTO.getCurrent(), queryDTO.getSize());
        SysFileConfig query = BeanCopyUtils.copyBean(queryDTO, SysFileConfig.class);
        return fileConfigMapper.selectFileConfigVOPage(page,query);
    }

    @Override
    public SysFileConfig getFileConfig(Long id) {
        return fileConfigMapper.selectById(id);
    }

    @Override
    public void deleteFileConfig(Long id) {
        // 校验存在
        SysFileConfig config = validateFileConfigExists(id);
        if (BooleanEnum.TRUE.getValue()==config.getMaster()) {
            throw new ServiceException("不能删除主配置项");
        }
        // 删除
        fileConfigMapper.deleteById(id);

        // 清空缓存
        clearCache(id, null);
    }

    /**
     * 校验名称的唯一性
     *
     * @param id   ID，用于排除自身
     * @param name 名称
     */
    private void validateNameUniqueness(Long id, String name) {
        SysFileConfig sysFileConfig = fileConfigMapper.selectOne(new LambdaQueryWrapper<SysFileConfig>().eq(SysFileConfig::getName, name));
        if (sysFileConfig != null && !sysFileConfig.getId().equals(id)) {
            throw new ServiceException(StatusCodeEnum.VALID_ERROR.getCode(), "配置名称不能重复");
        }
    }

    private SysFileConfig validateFileConfigExists(Long id) {
        SysFileConfig config = fileConfigMapper.selectById(id);
        if (config == null) {
            throw new ServiceException("此配置项不存在");
        }
        return config;
    }

    /**
     * 清空指定文件配置
     *
     * @param id 配置编号
     * @param master 是否主配置
     */
    private void clearCache(Long id, Integer master) {
        if (id != null) {
            clientCache.invalidate(id);
        }
        if (Objects.nonNull(master) && BooleanEnum.TRUE.getValue() ==master ) {
            clientCache.invalidate(CACHE_MASTER_ID);
        }
    }

}

