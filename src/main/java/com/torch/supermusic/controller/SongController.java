package com.torch.supermusic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.supermusic.entity.Song;
import com.torch.supermusic.service.ISongService;
import com.torch.supermusic.util.result.ResultUtils;
import com.torch.supermusic.util.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Torch
 * @since 2021-12-03
 */
@Controller
@RequestMapping("/song")
public class SongController {

    @Autowired
    private ISongService songService;

    @GetMapping()
    public ResultVo allSong(){
        return new ResultUtils().success("查询成功",songService.list());
    }

}
