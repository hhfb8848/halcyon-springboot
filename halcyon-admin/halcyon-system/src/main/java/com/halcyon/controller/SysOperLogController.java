package com.halcyon.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halcyon.annotation.AdminPrefix;
import com.halcyon.annotation.Log;
import com.halcyon.dao.entity.SysOperLog;
import com.halcyon.dto.log.OperLogQueryDTO;
import com.halcyon.enums.OperBusinessType;
import com.halcyon.service.SysOperLogService;
import com.halcyon.model.vo.ResponseResult;
import com.halcyon.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;

/**
 * 操作日志记录表(SysOperLog)表控制层
 *
 * @author sjh
 * @since 2024-04-25 10:36:46
 */
@AdminPrefix
@RequestMapping("/sysOperLog")
@RequiredArgsConstructor
public class SysOperLogController {
    /**
     * 服务对象
     */
    private final SysOperLogService operLogService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    public ResponseResult<Page<SysOperLog>> selectAll(OperLogQueryDTO queryDTO) {
        LambdaQueryWrapper<SysOperLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(queryDTO.getTitle()), SysOperLog::getTitle, queryDTO.getTitle());
        if (queryDTO.getOperTimeArr().length == 2) {
            queryWrapper.between(SysOperLog::getOperTime, queryDTO.getOperTimeArr()[0], queryDTO.getOperTimeArr()[1]);
        }

        queryWrapper.eq(Objects.nonNull(queryDTO.getStatus()), SysOperLog::getStatus, queryDTO.getStatus());
        return ResponseResult.ok(operLogService.page(queryDTO, queryWrapper));
    }


    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @Log(title = "操作日志管理", businessType = OperBusinessType.DELETE)
    @DeleteMapping("/delete")
    public ResponseResult<Boolean> delete(@RequestBody List<Long> idList) {
        return ResponseResult.ok(operLogService.removeByIds(idList));
    }

    /**
     * 清空日志
     * @return 删除结果
     */
    @Log(title = "操作日志管理", businessType = OperBusinessType.CLEAR)
    @DeleteMapping("/clear")
    public ResponseResult<Boolean> clear() {
        return ResponseResult.ok(operLogService.remove(null));
    }
}

