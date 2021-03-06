package com.torch.supermusic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.supermusic.entity.Singer;
import com.torch.supermusic.service.ISingerService;
import com.torch.supermusic.util.argEntity.SelectAndPage;
import com.torch.supermusic.entity.Song;
import com.torch.supermusic.service.ISongService;
import com.torch.supermusic.util.result.ResultUtils;
import com.torch.supermusic.util.result.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Torch
 * @since 2021-12-03
 */
@Api(tags = {"歌曲类"})
@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private ISongService songService;

    @Autowired
    private ISingerService singerService;

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("查询全部歌曲")
    @GetMapping()
    public ResultVo allSong(){
        List<Song> list = songService.list();
        for (Song song : list) {
            song.setSinger(singerService.getOne(new QueryWrapper<Singer>().eq("id",song.getSingerId())));
        }
        return ResultUtils.success("查询成功", list);
    }

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("模糊查询全部歌曲")
    @GetMapping("/like")
    public ResultVo likeSong(SelectAndPage selectAndPage){
        List<Song> list = songService.list(new QueryWrapper<Song>().like("name",selectAndPage.getArg()));
        for (Song song : list) {
            song.setSinger(singerService.getOne(new QueryWrapper<Singer>().eq("id",song.getSingerId())));
        }
        return ResultUtils.success("查询成功", list);
    }

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("根据歌曲名查询歌曲分页")
    @GetMapping("/page")
    public ResultVo PageSongAndSinger(SelectAndPage selectAndPage){
        long count = songService.count(new QueryWrapper<Song>().like("name", selectAndPage.getArg()));
        if (count%selectAndPage.getPageSize()==0){
            selectAndPage.setCurrentPage(selectAndPage.getCurrentPage()-1);
        }
        Page<Song> songPage = songService.page(new Page<Song>(selectAndPage.getCurrentPage(), selectAndPage.getPageSize()), new QueryWrapper<Song>().like("name", selectAndPage.getArg()));
        for (Song record : songPage.getRecords()) {
            record.setSinger(singerService.getById(record.getSingerId()));
        }
        return ResultUtils.success("查询成功", songPage);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("更新歌曲")
    @PutMapping("/updata")
    public ResultVo updata(@RequestBody Song song){
        return songService.updateById(song)? ResultUtils.success("修改成功"):ResultUtils.error("修改失败");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("添加歌曲")
    @PostMapping("/add")
    public ResultVo add(@RequestBody Song song){
        return songService.save(song)? ResultUtils.success("添加成功"):ResultUtils.error("添加失败");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("删除歌曲")
    @DeleteMapping(value = "/dels")
    public ResultVo delete(@RequestBody String[] ids){
        return songService.removeByIds(Arrays.asList(ids)) ? ResultUtils.success("删除成功") : ResultUtils.error("删除失败") ;
    }

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("查询歌手")
    @GetMapping("/singerByid/{id}")
    public ResultVo selectSingerId(@PathVariable("id") Long singerId){
        List<Song> songs = songService.list(new QueryWrapper<Song>().eq("singer_id", singerId));
        for (Song song : songs) {
            song.setSinger(singerService.getById(song.getSingerId()));
        }
        return ResultUtils.success("查询成功", songs);
    }


}
