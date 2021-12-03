package com.torch.supermusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Torch
 * @since 2021-12-03
 */
@Getter
@Setter
@TableName("singer")
@ApiModel(value = "Singer对象", description = "")
public class Singer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("歌手名字")
    @TableField("singer_name")
    private String singerName;

    @ApiModelProperty("国籍")
    @TableField("nationality")
    private String nationality;

    @ApiModelProperty("出生地址")
    @TableField("birth_address")
    private String birthAddress;

    @ApiModelProperty("性别（0：男，1：女）")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty("简介")
    @TableField("profile")
    private String profile;


}
