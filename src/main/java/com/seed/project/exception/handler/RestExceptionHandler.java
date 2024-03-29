package com.seed.project.exception.handler;

import com.seed.project.exception.BusinessException;
import com.seed.project.utils.DataResult;
import com.seed.project.exception.code.BaseResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.List;

/**
 * @ClassName: RestExceptionHandler
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public DataResult exception(Exception e){
        log.error("Exception,{},{}",e.getLocalizedMessage(),e);
        return DataResult.getResult(BaseResponseCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    public DataResult businessException(BusinessException e){
        log.error("businessException,{},{}",e.getLocalizedMessage(),e);
        return DataResult.getResult(e.getCode(),e.getMsg());
    }
    /**
     * 处理validation 框架异常
     * @Author:      小霍
     * @UpdateUser:
     * @Version:     0.0.1
     * @param e
     * @return       com.seed.project.utils.DataResult<T>
     * @throws
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    <T> DataResult<T> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        log.error("methodArgumentNotValidExceptionHandler bindingResult.allErrors():{},exception:{}", e.getBindingResult().getAllErrors(), e);
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return createValidExceptionResp(errors);
    }
    private <T> DataResult<T> createValidExceptionResp(List<ObjectError> errors) {
        String[] msgs = new String[errors.size()];
        int i = 0;
        for (ObjectError error : errors) {
            msgs[i] = error.getDefaultMessage();
            log.info("msg={}",msgs[i]);
            i++;
        }
        return DataResult.getResult(BaseResponseCode.METHOD_IDENTITY_ERROR.getCode(), msgs[0]);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public DataResult unauthorizedException(UnauthorizedException e){
        log.error("UnauthorizedException,{},{}",e.getLocalizedMessage(),e);
        return DataResult.getResult(BaseResponseCode.NOT_PERMISSION);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public DataResult maxUploadSizeExceededException(MaxUploadSizeExceededException e){
        log.error("MaxUploadSizeExceededException,{},{}",e,e.getLocalizedMessage());
        return DataResult.getResult(BaseResponseCode.FILE_TOO_LARGE);
    }
}
