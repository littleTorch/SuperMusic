package com.torch.supermusic.util.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 带参数返回值封装
 */
@Data
@AllArgsConstructor
@ApiModel(value = "ResultVo",description = "返回对象封装类")
public class ResultVo<T> {

    /**
     * 返回提示信息
     */
    @ApiModelProperty("返回提示信息")
    private String msg;

    /**
     * 返回状态码
     */
    @ApiModelProperty("返回状态码")
    private int code;

    /**
     * 返回数据
     */
    @ApiModelProperty("返回数据")
    private T data;
}
