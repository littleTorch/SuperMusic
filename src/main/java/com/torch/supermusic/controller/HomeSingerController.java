package com.torch.supermusic.controller;


import com.torch.supermusic.service.IHomeSingerService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Torch
 * @since 2021-12-14
 */
@Api(tags = {"首页歌手推荐类"})
@Controller
@RequestMapping("/home-singer")
public class HomeSingerController {

    @Autowired
    private IHomeSingerService homeSingerService;

}
