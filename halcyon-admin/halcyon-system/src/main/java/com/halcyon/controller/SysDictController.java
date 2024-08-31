package com.halcyon.controller;



import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halcyon.annotation.AdminPrefix;
import com.halcyon.exception.ServiceException;
import com.halcyon.utils.BeanCopyUtils;
import com.halcyon.utils.StringUtils;
import com.halcyon.dao.entity.SysDict;
import com.halcyon.dao.entity.SysDictData;
import com.halcyon.dto.dict.DictQueryDTO;
import com.halcyon.dto.dict.SysDictCreateDTO;
import com.halcyon.dto.dict.SysDictUpdateDTO;
import com.halcyon.service.SysDictDataService;
import com.halcyon.service.SysDictService;
import com.halcyon.model.vo.ResponseResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.halcyon.enums.StatusCodeEnum.FAIL;

/**
 * 字典表(SysDict)表控制层
 *
 * @author sjh
 * @since 2024-04-25 10:36:32
 */
@AdminPrefix
@RequestMapping("/sysDict")
@RequiredArgsConstructor
public class SysDictController  {
    /**
     * 服务对象
     */
    private final SysDictService sysDictService;

    private final SysDictDataService sysDictDataService;

    /**
     * 查询字典列表数据
     *
     * @param queryDTO 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    @SaCheckPermission("system:dict:query")
    public ResponseResult<Page<SysDict>> selectAll(DictQueryDTO queryDTO) {
        LambdaQueryWrapper<SysDict> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(queryDTO.getDictName()),SysDict::getDictName,queryDTO.getDictName())
                .like(StringUtils.isNotEmpty(queryDTO.getDictCode()),SysDict::getDictCode,queryDTO.getDictCode())
                .eq(Objects.nonNull(queryDTO.getStatus()),SysDict::getStatus,queryDTO.getStatus());
        return ResponseResult.ok(this.sysDictService.page(queryDTO,queryWrapper));
    }

    /**
     * 字典详情
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    public ResponseResult<SysDict> selectOne(@PathVariable Long id) {
        return ResponseResult.ok(this.sysDictService.getById(id));
    }

    /**
     * 新增字典
     *
     * @param createDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/create")
    @SaCheckPermission("system:dict:create")
    public ResponseResult<Long> insert(@Valid @RequestBody SysDictCreateDTO createDTO) {
        SysDict sysDict = BeanCopyUtils.copyBean(createDTO, SysDict.class);
        return ResponseResult.ok(this.sysDictService.createDict(sysDict));
    }

    /**
     * 修改字典
     *
     * @param updateDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @SaCheckPermission("system:dict:update")
    public ResponseResult<Long> update(@Valid @RequestBody SysDictUpdateDTO updateDTO) {
        SysDict sysDict = BeanCopyUtils.copyBean(updateDTO, SysDict.class);
        return ResponseResult.ok(this.sysDictService.updateDict(sysDict));
    }

    /**
     * 删除字典
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @SaCheckPermission("system:dict:delete")
    public ResponseResult<Boolean> delete(@PathVariable Long id) {
        long count = sysDictDataService.count(new LambdaQueryWrapper<SysDictData>().eq(SysDictData::getDictId, id));
        if (count>0){
            throw new ServiceException(FAIL.getCode(),"该字典下存在数据项，无法删除");
        }
        return ResponseResult.ok(this.sysDictService.removeById(id));
    }
}

