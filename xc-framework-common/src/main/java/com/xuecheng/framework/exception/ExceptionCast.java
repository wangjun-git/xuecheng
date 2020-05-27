package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/12 17:08
 */
public class ExceptionCast {
    // 抛出异常的方法
    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
