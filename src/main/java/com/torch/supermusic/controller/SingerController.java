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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping()
    public ResultVo allSinger(){
        return new ResultUtils().success("查询成功",singerService.list());
    }

    @ApiOperation("歌手分页")
    @GetMapping("/page")
    public ResultVo PageSinger(SelectAndPage selectAndPage){
        return ResultUtils.success("查询成功", singerService.page(new Page<Singer>(selectAndPage.getCurrentPage(), selectAndPage.getPageSize())));
    }

    @GetMapping("/byid/{id}")
    public ResultVo Singer(@PathVariable("id") Long id){
        return ResultUtils.success("查询成功",singerService.getById(id));
    }
}
