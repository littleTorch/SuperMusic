package com.torch.supermusic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.supermusic.entity.PlaylistSong;
import com.torch.supermusic.entity.Song;
import com.torch.supermusic.service.IPlaylistSongService;
import com.torch.supermusic.service.ISongService;
import com.torch.supermusic.util.result.ResultUtils;
import com.torch.supermusic.util.result.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Torch
 * @since 2021-12-03
 */
@Api(tags = {"歌单歌曲类"})
@RestController
@RequestMapping("/playlist-song")
public class PlaylistSongController {

    @Autowired
    private IPlaylistSongService playlistSongService;

    @Autowired
    private ISongService songService;

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("查询歌单")
    @GetMapping("/playlistid/{id}")
    public ResultVo getAll(@PathVariable("id") Long playlistId){
        List<PlaylistSong> list = playlistSongService.list(new QueryWrapper<PlaylistSong>().eq("playlist_id",playlistId));
        ArrayList<Long> ids = new ArrayList<>();
        for (PlaylistSong playlistSong : list) {
            ids.add(playlistSong.getSongId());
        }
        return ResultUtils.success("查询成功", songService.listByIds(ids));
    }
}
