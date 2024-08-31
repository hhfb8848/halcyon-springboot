package com.halcyon.exception;

import com.halcyon.enums.StatusCodeEnum;
import lombok.Data;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-04-25 11:06
 * @description: 业务异常
 */
@Data
public class ServiceException extends RuntimeException {

    /**
     * 状态码
     */
    private String code;

    /**
     * 描述
     */
    private String message;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException() {

    }

    public ServiceException(StatusCodeEnum statusCodeEnum) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getMessage();
    }
    public ServiceException(String code,String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 操作失败
     * @param message
     */
    public ServiceException(String message) {
        this.code = StatusCodeEnum.FAIL.getCode();
        this.message = message;
    }
}
