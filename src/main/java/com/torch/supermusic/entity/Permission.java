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
@TableName("permission")
@ApiModel(value = "Permission对象", description = "")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("父权限id")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty("父权限名")
    @TableField("parent_name")
    private String parentName;

    @ApiModelProperty("权限名称")
    @TableField("label")
    private String label;

    @ApiModelProperty("授权标识符")
    @TableField("code")
    private String code;

    @ApiModelProperty("路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty("路径名")
    @TableField("name")
    private String name;

    @ApiModelProperty("授权路径")
    @TableField("url")
    private String url;

    @ApiModelProperty("序号")
    @TableField("order_num")
    private String orderNum;

    @ApiModelProperty("类型（0目录，1菜单，2按钮）")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("图标路径")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("备注")
    @TableField("comment")
    private String comment;

    @TableField("is_home")
    private Integer isHome;


}
