package com.torch.supermusic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.supermusic.util.argEntity.SelectAndPage;
import com.torch.supermusic.entity.Song;
import com.torch.supermusic.service.ISongService;
import com.torch.supermusic.util.result.ResultUtils;
import com.torch.supermusic.util.result.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = {"歌曲类"})
@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private ISongService songService;

    @ApiOperation("查询全部歌曲")
    @GetMapping()
    public ResultVo allSong(){
        return ResultUtils.success("查询成功",songService.list());
    }

    @ApiOperation("根据歌曲名查询歌曲分页")
    @GetMapping("/page")
    public ResultVo PageSong(SelectAndPage selectAndPage){
        return ResultUtils.success("查询成功", songService.page(new Page<Song>(selectAndPage.getCurrentPage(), selectAndPage.getPageSize()),new QueryWrapper<Song>().like("name",selectAndPage.getArg())));
    }

    @ApiOperation("更新歌曲")
    @PutMapping("/updata")
    public ResultVo updata(@RequestBody Song song){
        return songService.updateById(song)? ResultUtils.success("修改成功"):ResultUtils.error("修改失败");
    }

    @ApiOperation("添加歌曲")
    @PostMapping("/add")
    public ResultVo add(@RequestBody Song song){
        return songService.save(song)? ResultUtils.success("添加成功"):ResultUtils.error("添加失败");
    }

    @ApiOperation("删除歌曲")
    @DeleteMapping(value = "/dels")
    public ResultVo delete(@RequestBody String[] ids){
        return songService.removeByIds(Arrays.asList(ids)) ? ResultUtils.success("删除成功") : ResultUtils.success("删除失败") ;
    }
}
