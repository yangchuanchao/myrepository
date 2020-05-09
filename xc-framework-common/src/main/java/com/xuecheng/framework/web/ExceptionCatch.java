package com.xuecheng.framework.web;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.exception.BaseException;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 使用@ControllerAdvice+@ExceptionHandler(Exception.class) 统一异常处理
 */
@Slf4j
@ControllerAdvice
public class ExceptionCatch {

    private static ImmutableMap<Class<? extends Throwable>, ResultCode> exMap;

    protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();

    static {
        builder.put(HttpMediaTypeNotSupportedException.class, CommonCode.INVALID_PARAM);
    }

    /**
     * 处理自定义异常
     *
     * @param baseException
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public ResponseResult baseException(BaseException baseException) {
        log.error("catch exception:{}", baseException.getMessage());
        ResponseResult responseResult = new ResponseResult();
        ResultCode resultCode = baseException.getResultCode();
        responseResult.setCode(resultCode.code());
        responseResult.setMessage(baseException.getMessage());
        responseResult.setSuccess(baseException.getResultCode().success());
        return responseResult;
    }

    /**
     * 未能捕获的异常，统一返回系统异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
//    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception ex) {
        log.error("can not catch exception:{}", ex.getMessage());
        if (exMap == null) {
            exMap = builder.build();
        }
        ResultCode resultCode = exMap.get(ex.getClass());
        ResponseResult responseResult;
        if (resultCode != null) {
            responseResult = new ResponseResult(resultCode);
        } else {
            responseResult = new ResponseResult(CommonCode.SERVER_ERROR);
        }
        return responseResult;
    }


}
