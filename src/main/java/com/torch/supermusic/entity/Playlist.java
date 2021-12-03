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
@TableName("playlist")
@ApiModel(value = "Playlist对象", description = "")
public class Playlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("歌单序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("歌单名字")
    @TableField("playlist_name")
    private String playlistName;

    @ApiModelProperty("歌单类型")
    @TableField("playlist_tag")
    private String playlistTag;

    @ApiModelProperty("创建者")
    @TableField("playlist_create")
    private String playlistCreate;

    @ApiModelProperty("备注")
    @TableField("playlist_comment")
    private String playlistComment;

    @ApiModelProperty("歌单类型（0普通歌单，1排行榜，2我的歌单）")
    @TableField("playlist_type")
    private Integer playlistType;

    @ApiModelProperty("歌单图片")
    @TableField("icon")
    private String icon;


}
