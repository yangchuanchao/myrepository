package com.xuecheng.framework.exception;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.xuecheng.framework.model.response.ResultCode;

public class ExceptionCast {

    public static void cast(BaseException baseException, ResultCode resultCode) {
        throw new BaseException(resultCode);
    }
}
