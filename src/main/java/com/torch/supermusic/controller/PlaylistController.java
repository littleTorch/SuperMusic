package com.torch.supermusic.controller;


import com.torch.supermusic.service.IPlaylistService;
import com.torch.supermusic.util.result.ResultUtils;
import com.torch.supermusic.util.result.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(tags = {"歌单类"})
@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private IPlaylistService playlistService;

    @ApiOperation("查询全部歌单")
    @GetMapping("")
    public ResultVo getAll(){
        return new ResultUtils().success("查询成功",playlistService.list());
    }

}
