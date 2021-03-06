package com.torch.supermusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
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
@TableName("playlist_song")
@ApiModel(value = "PlaylistSong对象", description = "")
public class PlaylistSong implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("歌曲序号")
    @TableField("song_id")
    private Long songId;

    @ApiModelProperty("歌单序号")
    @TableField("playlist_id")
    private Long playlistId;

    @ApiModelProperty(value = "歌单歌曲id")
    @TableField(exist = false)
    private List<Long> songIds;


}
