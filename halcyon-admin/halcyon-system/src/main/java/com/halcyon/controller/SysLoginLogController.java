package com.halcyon.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halcyon.annotation.AdminPrefix;
import com.halcyon.annotation.Log;
import com.halcyon.dao.entity.SysLoginLog;
import com.halcyon.dto.log.LoginLogQueryDTO;
import com.halcyon.enums.OperBusinessType;
import com.halcyon.service.SysLoginLogService;
import com.halcyon.model.vo.ResponseResult;
import com.halcyon.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 系统访问记录(SysLoginLog)表控制层
 *
 * @author sjh
 * @since 2024-04-25 10:36:46
 */
@AdminPrefix
@RequestMapping("/sysLoginLog")
@RequiredArgsConstructor
public class SysLoginLogController {
    /**
     * 服务对象
     */
    private final SysLoginLogService sysLoginLogService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    public ResponseResult<Page<SysLoginLog>> selectAll(LoginLogQueryDTO queryDTO) {
        LambdaQueryWrapper<SysLoginLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(queryDTO.getAccount()), SysLoginLog::getAccount, queryDTO.getAccount());
        if (queryDTO.getLoginTimeArr().length == 2) {
            queryWrapper.between(SysLoginLog::getLoginTime, queryDTO.getLoginTimeArr()[0], queryDTO.getLoginTimeArr()[1]);
        }

        queryWrapper.eq(Objects.nonNull(queryDTO.getStatus()), SysLoginLog::getStatus, queryDTO.getStatus());
        return ResponseResult.ok(this.sysLoginLogService.page(queryDTO, queryWrapper));
    }


    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @Log(title = "登录日志管理", businessType = OperBusinessType.DELETE)
    @DeleteMapping("/delete")
    public ResponseResult<Boolean> delete(@RequestBody List<Long> idList) {
        return ResponseResult.ok(this.sysLoginLogService.removeByIds(idList));
    }

    /**
     * 清空日志
     * @return 删除结果
     */
    @Log(title = "登录日志管理", businessType = OperBusinessType.CLEAR)
    @DeleteMapping("/clear")
    public ResponseResult<Boolean> clear() {
        return ResponseResult.ok(this.sysLoginLogService.remove(null));
    }
}

