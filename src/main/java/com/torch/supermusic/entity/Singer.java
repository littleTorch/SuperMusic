package com.torch.supermusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
@Data
@TableName("singer")
@ApiModel(value = "Singer对象", description = "")
public class Singer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("歌手名字")
    @TableField("singer_name")
    private String singerName;

    @ApiModelProperty("简介")
    @TableField("profile")
    private String profile;

    @ApiModelProperty("歌手照片")
    @TableField("icon")
    private String icon;


}
