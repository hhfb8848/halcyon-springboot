package com.halcyon.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halcyon.annotation.AdminPrefix;
import com.halcyon.dao.entity.SysLoginLog;
import com.halcyon.service.SysLoginLogService;
import com.halcyon.model.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param page 分页对象
     * @param sysLoginLog 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public ResponseResult<Page<SysLoginLog>> selectAll(Page<SysLoginLog> page, SysLoginLog sysLoginLog) {
        return ResponseResult.ok(this.sysLoginLogService.page(page, new QueryWrapper<>(sysLoginLog)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    public ResponseResult<SysLoginLog> selectOne(@PathVariable Long id) {
        return ResponseResult.ok(this.sysLoginLogService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysLoginLog 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseResult<Boolean> insert(@RequestBody SysLoginLog sysLoginLog) {
        return ResponseResult.ok(this.sysLoginLogService.save(sysLoginLog));
    }

    /**
     * 修改数据
     *
     * @param sysLoginLog 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseResult<Boolean> update(@RequestBody SysLoginLog sysLoginLog) {
        return ResponseResult.ok(this.sysLoginLogService.updateById(sysLoginLog));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseResult<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return ResponseResult.ok(this.sysLoginLogService.removeByIds(idList));
    }
}

