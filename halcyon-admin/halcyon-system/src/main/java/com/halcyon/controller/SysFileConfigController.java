package com.halcyon.controller;



import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.halcyon.convert.file.FileConfigConvert;
import com.halcyon.annotation.AdminPrefix;
import com.halcyon.file.FileStorageEnum;
import com.halcyon.file.client.FileClientConfig;
import com.halcyon.utils.BeanCopyUtils;
import com.halcyon.utils.JsonUtils;
import com.halcyon.utils.ValidationUtils;
import com.halcyon.dao.entity.SysFileConfig;
import com.halcyon.dto.file.FileConfigCreateDTO;
import com.halcyon.dto.file.FileConfigQueryDTO;
import com.halcyon.dto.file.FileConfigUpdateDTO;
import com.halcyon.service.SysFileConfigService;
import com.halcyon.model.vo.ResponseResult;
import com.halcyon.vo.file.FileConfigSimpleVO;
import com.halcyon.vo.file.FileConfigSingleVO;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 文件配置表(SysFileConfig)表控制层
 *
 * @author sjh
 * @since 2024-07-30 14:30:28
 */
@AdminPrefix
@RequestMapping("/sysFileConfig")
@RequiredArgsConstructor
public class SysFileConfigController{
    /**
     * 服务对象
     */
    private final SysFileConfigService sysFileConfigService;

    private final Validator validator;

    /**
     * 分页查询文件配置
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @SaCheckPermission("system:file:config:query")
    public ResponseResult<IPage<FileConfigSimpleVO>> selectAll(FileConfigQueryDTO queryDTO) {
        return ResponseResult.ok(sysFileConfigService.pageFileConfigVO(queryDTO));
    }

    /**
     * 获得文件配置详情
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    @SaCheckPermission("system:file:config:detail")
    public ResponseResult<FileConfigSingleVO> selectOne(@PathVariable Long id) {
        SysFileConfig fileConfig = this.sysFileConfigService.getFileConfig(id);
        FileConfigSingleVO fileConfigSingleVO = BeanCopyUtils.copyBean(fileConfig, FileConfigSingleVO.class);
        return ResponseResult.ok(fileConfigSingleVO);
    }

    /**
     * 创建文件配置
     *
     * @param createDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/create")
    @SaCheckPermission("system:file:config:create")
    public ResponseResult<Long> insert(@Valid @RequestBody FileConfigCreateDTO createDTO) {
        SysFileConfig fileConfig = FileConfigConvert.INSTANCE.convertCreateDTO(createDTO);
        fileConfig.setConfig(parseClientConfig(createDTO.getStorage(), createDTO.getConfig()));
        return ResponseResult.ok(this.sysFileConfigService.createFileConfig(fileConfig));
    }

    /**
     * 修改文件配置
     *
     * @param updateDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @SaCheckPermission("system:file:config:update")
    public ResponseResult<Long> update(@Valid @RequestBody FileConfigUpdateDTO updateDTO) {
        SysFileConfig fileConfig = FileConfigConvert.INSTANCE.convertUpdateDTO(updateDTO);
        fileConfig.setConfig(parseClientConfig(updateDTO.getStorage(), updateDTO.getConfig()));
        return ResponseResult.ok(this.sysFileConfigService.updateFileConfig(fileConfig));
    }

    /**
     * 设为主配置
     */
    @PutMapping("/updateMaster/{id}")
    @SaCheckPermission("system:file:config:updateMaster")
    public ResponseResult<Boolean> updateFileConfigMaster(@PathVariable Long id) {
        sysFileConfigService.updateFileConfigMaster(id);
        return ResponseResult.ok(true);
    }

    /**
     * 删除配置
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @SaCheckPermission("system:file:config:delete")
    public ResponseResult<Boolean> delete(@PathVariable Long id) {
        this.sysFileConfigService.deleteFileConfig(id);
        return ResponseResult.ok(true);
    }

    private FileClientConfig parseClientConfig(Integer storage, Map<String, Object> config) {
        // 获取配置类
        Class<? extends FileClientConfig> configClass = FileStorageEnum.getByStorage(storage)
                .getConfigClass();
        FileClientConfig clientConfig = JsonUtils.parseObject2(JsonUtils.toJsonString(config), configClass);
        // 参数校验
        ValidationUtils.validate(validator, clientConfig);
        // 设置参数
        return clientConfig;
    }
}

