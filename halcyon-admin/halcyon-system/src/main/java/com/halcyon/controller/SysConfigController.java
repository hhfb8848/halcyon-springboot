package com.halcyon.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halcyon.convert.sysConfig.SysConfigConvert;
import com.halcyon.annotation.AdminPrefix;
import com.halcyon.enums.BooleanEnum;
import com.halcyon.exception.ServiceException;
import com.halcyon.utils.BeanCopyUtils;
import com.halcyon.utils.StringUtils;
import com.halcyon.dao.entity.SysConfig;
import com.halcyon.dto.sysConfig.SysConfigCreateDTO;
import com.halcyon.dto.sysConfig.SysConfigQueryDTO;
import com.halcyon.dto.sysConfig.SysConfigUpdateDTO;
import com.halcyon.service.SysConfigService;
import com.halcyon.model.vo.ResponseResult;
import com.halcyon.vo.sysConfig.ConfigSingleVO;
import com.halcyon.vo.sysConfig.SysConfigSimpleVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-08-02 14:46
 * @description: 系统配置
 */
@AdminPrefix
@RequestMapping("/sysConfig")
@RequiredArgsConstructor
public class SysConfigController {


    private final SysConfigService configService;

    /**
     * 系统配置列表
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @SaCheckPermission("system:config:query")
    public ResponseResult<IPage<SysConfigSimpleVO>> selectAll(SysConfigQueryDTO queryDTO) {
        LambdaQueryWrapper<SysConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(queryDTO.getConfigName()), SysConfig::getConfigName, queryDTO.getConfigName());
        Page<SysConfig> configPage = configService.page(new Page<SysConfig>(queryDTO.getCurrent(), queryDTO.getSize()), queryWrapper);
        Page<SysConfigSimpleVO> sysConfigSimpleVOPage = SysConfigConvert.INSTANCE.convertSimplePage(configPage);
        return ResponseResult.ok(sysConfigSimpleVOPage);
    }

    /**
     * 获得配置详情
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    public ResponseResult<ConfigSingleVO> selectOne(@PathVariable Long id) {
        SysConfig fileConfig = this.configService.getById(id);
        ConfigSingleVO configSingleVO = BeanCopyUtils.copyBean(fileConfig, ConfigSingleVO.class);
        return ResponseResult.ok(configSingleVO);
    }

    /**
     * 创建系统配置
     *
     * @param createDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/create")
    @SaCheckPermission("system:config:create")
    public ResponseResult<Integer> insert(@Valid @RequestBody SysConfigCreateDTO createDTO) {
        SysConfig config = SysConfigConvert.INSTANCE.convertCreateDTO(createDTO);
        return ResponseResult.ok(configService.createConfig(config));
    }

    /**
     * 修改系统配置
     *
     * @param updateDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @SaCheckPermission("system:config:update")
    public ResponseResult<Integer> update(@Valid @RequestBody SysConfigUpdateDTO updateDTO) {
        SysConfig config = SysConfigConvert.INSTANCE.convertUpdateDTO(updateDTO);
        return ResponseResult.ok(configService.updateConfig(config));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @SaCheckPermission("system:config:delete")
    public ResponseResult<Boolean> delete(@PathVariable Long id) {
        SysConfig sysConfig = configService.getById(id);
        if (sysConfig.getType().equals(BooleanEnum.TRUE.getValue())) {
            throw new ServiceException("系统内置配置无法删除");
        }
        return ResponseResult.ok(configService.removeById(id));
    }

    /**
     * 缓存刷新
     *
     */
    @GetMapping("/refreshCache")
    @SaCheckPermission("system:config:refreshCache")
    public ResponseResult<Void> refreshCache() {
        configService.resetConfigCache();
        return ResponseResult.ok();
    }

    /**
     * 根据key获取配置
     *
     * @return 单条数据
     */
    @SaIgnore
    @GetMapping("/getByKey/{configKey}")
    public ResponseResult<Object> selectByKey(@PathVariable String configKey) {
        return ResponseResult.ok(JSONObject.parseObject(configService.getConfigValueByKey(configKey)));
    }

}
