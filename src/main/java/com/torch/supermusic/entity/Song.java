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
@TableName("song")
@ApiModel(value = "Song对象", description = "")
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("歌曲id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("歌曲名字")
    @TableField("name")
    private String name;

    @ApiModelProperty("歌手id")
    @TableField("singer_id")
    private Integer singerId;

    @ApiModelProperty("歌曲文件地址")
    @TableField("song_url")
    private String songUrl;

    @ApiModelProperty("歌曲封面地址")
    @TableField("picture_url")
    private String pictureUrl;

    @ApiModelProperty("歌单名字")
    @TableField("playlist_name")
    private String playlistName;

    @ApiModelProperty("备注")
    @TableField("comment")
    private String comment;


}
