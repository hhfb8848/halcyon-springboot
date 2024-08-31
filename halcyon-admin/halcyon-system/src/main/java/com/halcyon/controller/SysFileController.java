package com.halcyon.controller;



import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.halcyon.annotation.AdminPrefix;
import com.halcyon.utils.BeanCopyUtils;
import com.halcyon.dao.entity.SysFile;
import com.halcyon.dto.file.FileCreateDTO;
import com.halcyon.dto.file.FileQueryDTO;
import com.halcyon.dto.file.FileUploadDTO;
import com.halcyon.service.SysFileService;
import com.halcyon.model.vo.ResponseResult;
import com.halcyon.vo.file.FilePreSignedUrlVO;
import com.halcyon.vo.file.FileSimpleVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import static com.halcyon.utils.FileTypeUtils.writeAttachment;

/**
 * 文件表(SysFile)表控制层
 *
 * @author sjh
 * @since 2024-07-30 14:30:13
 */
@AdminPrefix
@RequestMapping("/sysFile")
@RequiredArgsConstructor
@Slf4j
public class SysFileController  {
    /**
     * 服务对象
     */
    private final SysFileService fileService;

    /**
     * 分页查询所有文件
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @SaCheckPermission("system:file:list:query")
    public ResponseResult<IPage<FileSimpleVO>> selectAll(FileQueryDTO queryDTO) {
        return ResponseResult.ok(fileService.pageFile(queryDTO));
    }

    /**
     * 模式一：后端上传文件
     */
    @PostMapping("/upload")
    public ResponseResult<String> uploadFile(@Valid FileUploadDTO uploadDTO) throws Exception {
        MultipartFile file = uploadDTO.getFile();
        String path = uploadDTO.getPath();
        return ResponseResult.ok(fileService.createFile(file.getOriginalFilename(), path, IoUtil.readBytes(file.getInputStream())));
    }

    /**
     * 模式二：前端上传文件：用于前端直接上传七牛、阿里云 OSS 等文件存储器
     */
    @GetMapping("/pre-signed-url")
    public ResponseResult<FilePreSignedUrlVO> getFilePreSignedUrl(@RequestParam("path") String path) throws Exception {
        return ResponseResult.ok(fileService.getFilePreSignedUrl(path));
    }

    /**
     * 模式二：前端上传文件：用于前端直接上传七牛、阿里云 OSS 等文件存储器
     */
    @PostMapping("/create")
    public ResponseResult<Long> createFile(@Valid @RequestBody FileCreateDTO createDTO) {
        SysFile sysFile = BeanCopyUtils.copyBean(createDTO, SysFile.class);
        sysFile.setCreateBy(Long.valueOf(StpUtil.getLoginId().toString()));
        return ResponseResult.ok(fileService.createFile(sysFile));
    }

    /**
     * 删除文件
     */
    @DeleteMapping("/delete/{id}")
    @SaCheckPermission("system:file:list:delete")
    public ResponseResult<Boolean> deleteFile(@PathVariable Long id) throws Exception {
        fileService.deleteFile(id);
        return ResponseResult.ok(true);
    }

    /**
     * 下载文件
     */
    @GetMapping("/check-file/{configId}/get/**")
    public void getFileContent(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("configId") Long configId) throws Exception {
        // 获取请求的路径
        String path = StrUtil.subAfter(request.getRequestURI(), "/get/", false);
        if (StrUtil.isEmpty(path)) {
            throw new IllegalArgumentException("结尾的 path 路径必须传递");
        }
        // 解码，解决中文路径的问题 https://gitee.com/zhijiantianya/ruoyi-vue-pro/pulls/807/
        path = URLUtil.decode(path);

        // 读取内容
        byte[] content = fileService.getFileContent(configId, path);
        if (content == null) {
            log.warn("[getFileContent][configId({}) path({}) 文件不存在]", configId, path);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }
        writeAttachment(response, path, content);
    }
}

