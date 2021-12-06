package com.torch.supermusic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.supermusic.util.climbing.GetMusic;
import com.torch.supermusic.service.IPlaylistService;
import com.torch.supermusic.service.IPlaylistSongService;
import com.torch.supermusic.service.ISingerService;
import com.torch.supermusic.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GetMusicController {

    @Autowired
    private IPlaylistService playlistService;
    @Autowired
    private IPlaylistSongService playlistSongService;
    @Autowired
    private ISongService songService;
    @Autowired
    private ISingerService singerService;

    @GetMapping("/getmusic")
    public void getmusic(){
        delAllAboutMusicTabel();
        Request request = new Request("http://localhost:3000/login/cellphone");
        request.setMethod(HttpConstant.Method.POST);
        Map<String, Object> map = new HashMap<>();
        map.put("phone","15815115306");
        map.put("password","abc123456");
        request.setRequestBody(HttpRequestBody.form(map,"utf-8"));
        Spider.create(new GetMusic(playlistService,playlistSongService,songService,singerService))
                .addRequest(request)
                .addUrl("http://localhost:3000/toplist")
                .addPipeline(new ConsolePipeline())
                //开启5个线程抓取
                .thread(8)
                //启动爬虫
                .run();
    }


    public void delAllAboutMusicTabel(){
        playlistService.remove(new QueryWrapper<>());
        playlistSongService.remove(new QueryWrapper<>());
        songService.remove(new QueryWrapper<>());
        singerService.remove(new QueryWrapper<>());
    }
}
