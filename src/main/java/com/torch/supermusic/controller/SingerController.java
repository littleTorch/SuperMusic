package com.torch.supermusic.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.supermusic.entity.Song;
import com.torch.supermusic.util.argEntity.SelectAndPage;
import io.swagger.annotations.Api;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.supermusic.entity.Singer;
import com.torch.supermusic.service.ISingerService;
import com.torch.supermusic.util.result.ResultUtils;
import com.torch.supermusic.util.result.ResultVo;
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
@Api(tags = {"歌手类"})
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private ISingerService singerService;

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("全部歌手")
    @GetMapping()
    public ResultVo allSinger(){
        return new ResultUtils().success("查询成功",singerService.list());
    }

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("歌手分页")
    @GetMapping("/page")
    public ResultVo PageSinger(SelectAndPage selectAndPage){
        return ResultUtils.success("查询成功", singerService.page(new Page<Singer>(selectAndPage.getCurrentPage(), selectAndPage.getPageSize()), new QueryWrapper<Singer>().like("singer_name", selectAndPage.getArg())));
    }

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("查询歌手")
    @GetMapping("/byid/{id}")
    public ResultVo Singer(@PathVariable("id") Long id){
        return ResultUtils.success("查询成功",singerService.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("更新歌曲")
    @PutMapping("/updata")
    public ResultVo updata(@RequestBody Singer singer){
        return singerService.updateById(singer)? ResultUtils.success("修改成功"):ResultUtils.error("修改失败");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("添加歌曲")
    @PostMapping("/add")
    public ResultVo add(@RequestBody Singer singer){
        return singerService.save(singer)? ResultUtils.success("添加成功"):ResultUtils.error("添加失败");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("删除歌曲")
    @DeleteMapping(value = "/dels")
    public ResultVo delete(@RequestBody String[] ids){
        return singerService.removeByIds(Arrays.asList(ids)) ? ResultUtils.success("删除成功") : ResultUtils.success("删除失败") ;
    }
}
