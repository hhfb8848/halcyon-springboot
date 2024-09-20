package com.halcyon.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.halcyon.annotation.AdminPrefix;
import com.halcyon.annotation.Log;
import com.halcyon.annotation.RepeatSubmit;
import com.halcyon.enums.BusinessStatusEnum;
import com.halcyon.enums.OperBusinessType;
import com.halcyon.enums.NoticeTypeEnum;
import com.halcyon.exception.ServiceException;
import com.halcyon.dto.notice.NoticeCreateDTO;
import com.halcyon.dto.notice.NoticeQueryDTO;
import com.halcyon.dto.notice.NoticeUpdateDTO;
import com.halcyon.service.SysNoticeService;
import com.halcyon.service.SysUserService;
import com.halcyon.model.vo.ResponseResult;
import com.halcyon.vo.notice.NoticeDetailVO;
import com.halcyon.vo.notice.NoticePageVO;
import com.halcyon.vo.notice.NoticeSimpleVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-08-04 20:50
 * @description: 通知公告
 */
@AdminPrefix
@RequestMapping("/sysNotice")
@RequiredArgsConstructor
public class SysNoticeController {
    /**
     * 服务对象
     */
    private final SysUserService sysUserService;

    private final SysNoticeService noticeService;

    /**
     * 公告分页
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @SaCheckPermission("system:notice:query")
    public ResponseResult<IPage<NoticeSimpleVO>> selectAll(NoticeQueryDTO queryDTO) {
        return ResponseResult.ok(noticeService.pageNotice(queryDTO));
    }

    /**
     * 公告详情
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/getDetail/{id}")
    public ResponseResult<NoticeDetailVO> selectOne(@PathVariable Long id) {
        return ResponseResult.ok(noticeService.getNotice(id));
    }

    /**
     * 新增公告
     *
     * @param createDTO 实体对象
     * @return 新增结果
     */
    @Log(title = "公告管理",businessType = OperBusinessType.INSERT)
    @PostMapping("/create")
    @RepeatSubmit
    @SaCheckPermission("system:notice:create")
    public ResponseResult<Long> insert(@Valid @RequestBody NoticeCreateDTO createDTO) {
        if (createDTO.getType().equals(NoticeTypeEnum.NOTIFICATION.getType())&& createDTO.getRoleIds().size()==0){
            throw new ServiceException("通知角色不能为空");
        }
        return ResponseResult.ok(noticeService.createNotice(createDTO));
    }

    /**
     * 修改公告
     *
     * @param updateDTO 实体对象
     * @return 修改结果
     */
    @Log(title = "公告管理",businessType = OperBusinessType.UPDATE)
    @PutMapping("/update")
    @SaCheckPermission("system:notice:update")
    public ResponseResult<Long> update(@Valid @RequestBody NoticeUpdateDTO updateDTO) {
        return ResponseResult.ok(noticeService.updateNotice(updateDTO));
    }


    /**
     * 删除公告
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @Log(title = "公告管理",businessType = OperBusinessType.DELETE)
    @DeleteMapping("/delete")
    @SaCheckPermission("system:notice:delete")
    public ResponseResult<Boolean> delete(@RequestBody List<Long> idList) {
        return ResponseResult.ok(noticeService.deleteNotices(idList));
    }

    /**
     * 根据用户查询通知公告
     *
     * @return 所有数据
     */
    @GetMapping("/listByUser")
    public ResponseResult<NoticePageVO> selectByUser(NoticeQueryDTO queryDTO) {
        // 查正常的
        queryDTO.setStatus(BusinessStatusEnum.ACTIVE.getValue());
        return ResponseResult.ok(noticeService.pageNoticeByUser(queryDTO));
    }

    /**
     * 设为已读
     *
     * @param id 主键
     */
    @PostMapping("/setRead/{id}")
    public ResponseResult<Boolean> setRead(@PathVariable Long id) {
        return ResponseResult.ok(noticeService.setRead(id));
    }
}
