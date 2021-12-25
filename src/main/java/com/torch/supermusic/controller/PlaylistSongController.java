package com.torch.supermusic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.supermusic.entity.PlaylistSong;
import com.torch.supermusic.entity.Song;
import com.torch.supermusic.service.IPlaylistSongService;
import com.torch.supermusic.service.ISingerService;
import com.torch.supermusic.service.ISongService;
import com.torch.supermusic.util.argEntity.SelectAndPage;
import com.torch.supermusic.util.result.ResultUtils;
import com.torch.supermusic.util.result.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
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

    @Autowired
    private ISingerService singerService;

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("查询歌单")
    @GetMapping("/playlistid/{id}")
    public ResultVo getAll(@PathVariable("id") Long playlistId) {
        List<PlaylistSong> list = playlistSongService.list(new QueryWrapper<PlaylistSong>().eq("playlist_id", playlistId));
        ArrayList<Long> ids = new ArrayList<>();
        for (PlaylistSong playlistSong : list) {
            ids.add(playlistSong.getSongId());
        }
        List<Song> songs = songService.listByIds(ids);
        for (Song song : songs) {
            song.setSinger(singerService.getById(song.getSingerId()));
        }
        return ResultUtils.success("查询成功", songs);
    }

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("根据歌单ID查询歌曲分页")
    @GetMapping("/page")
    public ResultVo PageSongAndSinger(SelectAndPage selectAndPage) {
        long count = playlistSongService.count(new QueryWrapper<PlaylistSong>().eq("playlist_id", selectAndPage.getArg()));
        if (count % selectAndPage.getPageSize() == 0 && count / selectAndPage.getPageSize() < selectAndPage.getCurrentPage()) {
            selectAndPage.setCurrentPage(selectAndPage.getCurrentPage() - 1);
        }
        List<PlaylistSong> playlistSongs = playlistSongService.list(new QueryWrapper<PlaylistSong>().eq("playlist_id", selectAndPage.getArg()));
        ArrayList songIds = new ArrayList();
        for (PlaylistSong playlistSong : playlistSongs) {
            songIds.add(playlistSong.getSongId());
        }
        Page<Song> songPage = songService.page(new Page<Song>(selectAndPage.getCurrentPage(), selectAndPage.getPageSize()), new QueryWrapper<Song>().in("id", songIds));
        for (Song record : songPage.getRecords()) {
            record.setSinger(singerService.getById(record.getSingerId()));
        }
        return ResultUtils.success("查询成功", songPage);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("添加歌单歌曲")
    @PostMapping("/add")
    public ResultVo add(@RequestBody PlaylistSong playlistSong) {
        Boolean flag = true;
        try {
            for (Long songId : playlistSong.getSongIds()) {
                PlaylistSong ps = new PlaylistSong();
                ps.setSongId(songId);
                ps.setPlaylistId(playlistSong.getPlaylistId());
                if (playlistSongService.count(new QueryWrapper<PlaylistSong>().eq("song_id",ps.getSongId()).eq("playlist_id",ps.getPlaylistId()))==0){
                    playlistSongService.save(ps);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            return flag ? ResultUtils.success("添加成功") : ResultUtils.error("添加失败");
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("删除歌单歌曲")
    @DeleteMapping(value = "/dels")
    //懒得写一个接收类，将就用分页查询的接收类
    public ResultVo delete(@RequestBody List<SelectAndPage> selectAndPages) {
        Boolean flag = true;
        try {
            for (SelectAndPage selectAndPage : selectAndPages) {
                playlistSongService.remove(new QueryWrapper<PlaylistSong>().eq("song_id", selectAndPage.getArg()).eq("playlist_id", selectAndPage.getArg2()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            return flag ? ResultUtils.success("删除成功") : ResultUtils.error("删除失败");
        }

    }

}
