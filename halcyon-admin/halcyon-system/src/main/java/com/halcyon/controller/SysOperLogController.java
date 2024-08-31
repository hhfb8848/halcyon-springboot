package com.halcyon.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halcyon.annotation.AdminPrefix;
import com.halcyon.dao.entity.SysOperLog;
import com.halcyon.service.SysOperLogService;
import com.halcyon.model.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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
    private final SysOperLogService sysOperLogService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param sysOperLog 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public ResponseResult<Page<SysOperLog>> selectAll(Page<SysOperLog> page, SysOperLog sysOperLog) {
        return ResponseResult.ok(this.sysOperLogService.page(page, new QueryWrapper<>(sysOperLog)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    public ResponseResult<SysOperLog> selectOne(@PathVariable Long id) {
        return ResponseResult.ok(this.sysOperLogService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysOperLog 实体对象
     * @return 新增结果
     */
    @PostMapping("/create")
    public ResponseResult<Boolean> insert(@RequestBody SysOperLog sysOperLog) {
        return ResponseResult.ok(this.sysOperLogService.save(sysOperLog));
    }

    /**
     * 修改数据
     *
     * @param sysOperLog 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public ResponseResult<Boolean> update(@RequestBody SysOperLog sysOperLog) {
        return ResponseResult.ok(this.sysOperLogService.updateById(sysOperLog));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseResult<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return ResponseResult.ok(this.sysOperLogService.removeByIds(idList));
    }
}

