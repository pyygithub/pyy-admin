package com.thtf.common.core.exception;

import com.thtf.common.core.response.ResponseCode;
import lombok.Data;

/**
 * ---------------------------
 * 自定义异常类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/26 17:24
 * 版本：  v1.0
 * ---------------------------
 */
@Data
public class CustomException extends RuntimeException {

    private ResponseCode responseCode;

    public CustomException(ResponseCode responseCode) {
        //异常信息为错误代码+异常信息
        super("错误代码："+responseCode.code()+" <br/>错误信息："+responseCode.message());
        this.responseCode = responseCode;
    }

}
