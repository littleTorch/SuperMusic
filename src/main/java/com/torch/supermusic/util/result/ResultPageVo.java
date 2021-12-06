package com.torch.supermusic.util.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 带分页返回值封装
 */

@Data
@AllArgsConstructor
public class ResultPageVo<T> {
    /**
     * 返回提示信息
     */
    private String msg;
    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 当前第几页
     */
    private Integer pageNum;
    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * 总条数
     */
    private Integer total;
    /**
     * 返回数据
     */
    private T data;
}