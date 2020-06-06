package com.seed.project.exception;

import com.seed.project.exception.code.ResponseCodeInterface;

/**
 * @ClassName: BusinessException
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public class BusinessException extends RuntimeException {

    /**
     * 提示编码
     */
    private int code;

    /**
     * 后端提示语
     */
    private String msg;

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String msg, Throwable c) {
        super(msg, c);
    }

    public BusinessException(ResponseCodeInterface responseCodeInterface) {
        this(responseCodeInterface.getCode(), responseCodeInterface.getMsg());
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
