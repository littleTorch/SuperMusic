package com.torch.supermusic.util.argEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "SelectAndPage",description = "查询分页参数工具类")
public class SelectAndPage {

    @ApiModelProperty("当前页")
    private int currentPage;

    @ApiModelProperty("每页数据量/条")
    private int pageSize;

    @ApiModelProperty("参数1")
    private String arg;

    @ApiModelProperty("参数2")
    private String arg2;

    public String getArg() {
        if (arg==null)
            return "";
        return arg;
    }

    public String getArg2() {
        if (arg2==null)
            return "";
        return arg2;
    }
}
