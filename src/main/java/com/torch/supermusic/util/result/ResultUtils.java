package com.torch.supermusic.util.result;


/**
 * 数据返回工具类
 */
public class ResultUtils {

    public static ResultVo Vo(String msg, int code, Object data) {
        return new ResultVo(msg, code, data);
    }
    //返回成功
    //无参数返回值
    public static ResultVo success() {
        return Vo(null, CodeStatus.SUCCESS_CODE, null);
    }

    //一个参数返回值
    public static ResultVo success(String msg) {
        return Vo(msg, CodeStatus.SUCCESS_CODE, null);
    }

    //全部参数
    public static ResultVo success(String msg, int code, Object data) {
        return Vo(msg, code, data);
    }

    //提示信息和返回数据
    public static ResultVo success(String msg, Object data) {
        return Vo(msg, CodeStatus.SUCCESS_CODE, data);
    }


    //返回失败
    //无参数返回值
    public static ResultVo error() {
        return Vo(null, CodeStatus.ERROR_CODE, null);
    }

    //一个参数返回值
    public static ResultVo error(String msg) {
        return Vo(msg, CodeStatus.ERROR_CODE, null);
    }

    //全部参数
    public static ResultVo error(String msg, int code, Object data) {
        return Vo(msg, code, data);
    }

    public static ResultVo error(String msg, int code) {
        return Vo(msg, code, null);
    }

    //提示信息和返回数据
    public static ResultVo error(String msg, Object data) {
        return Vo(msg, CodeStatus.ERROR_CODE, data);
    }
}
