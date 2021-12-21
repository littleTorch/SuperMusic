package com.torch.supermusic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.supermusic.entity.Playlist;
import com.torch.supermusic.service.IPlaylistService;
import com.torch.supermusic.util.argEntity.SelectAndPage;
import com.torch.supermusic.util.result.ResultUtils;
import com.torch.supermusic.util.result.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Torch
 * @since 2021-12-03
 */
@Api(tags = {"歌单类"})
@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private IPlaylistService playlistService;

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("查询全部歌单")
    @GetMapping("")
    public ResultVo getAll(){
        return new ResultUtils().success("查询成功",playlistService.list());
    }

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("根据歌单名查询歌单分页")
    @GetMapping("/page")
    public ResultVo PageSong(SelectAndPage selectAndPage){
        return ResultUtils.success("查询成功", playlistService.page(new Page<Playlist>(selectAndPage.getCurrentPage(), selectAndPage.getPageSize()),new QueryWrapper<Playlist>().eq("playlist_type",selectAndPage.getArg2()).like("playlist_name",selectAndPage.getArg())));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("更新歌单")
    @PutMapping("/updata")
    public ResultVo updata(@RequestBody Playlist playlist){
        return playlistService.updateById(playlist)? ResultUtils.success("修改成功"):ResultUtils.error("修改失败");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("添加歌单")
    @PostMapping("/add")
    public ResultVo add(@RequestBody Playlist playlist){
        return playlistService.save(playlist)? ResultUtils.success("添加成功"):ResultUtils.error("添加失败");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("删除歌单")
    @DeleteMapping(value = "/dels")
    public ResultVo delete(@RequestBody String[] ids){
        return playlistService.removeByIds(Arrays.asList(ids)) ? ResultUtils.success("删除成功") : ResultUtils.success("删除失败") ;
    }
}