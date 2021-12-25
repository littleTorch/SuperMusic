package com.torch.supermusic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.supermusic.entity.Song;
import com.torch.supermusic.util.climbing.GetMusic;
import com.torch.supermusic.service.IPlaylistService;
import com.torch.supermusic.service.IPlaylistSongService;
import com.torch.supermusic.service.ISingerService;
import com.torch.supermusic.service.ISongService;
import com.torch.supermusic.util.climbing.GetSongList;
import com.torch.supermusic.util.climbing.UpdataSong;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"音乐数据类"})
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

    @ApiOperation("更新数据库")
    @GetMapping("/getmusic")
    //会把全部删掉重新写入
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
                .thread(10)
                //启动爬虫
                .run();
        clearSong();
    }

    @ApiOperation("更新歌单")
    @GetMapping("/getSongList")
    //会把全部删掉重新写入
    public void getSongList(){
        Request request = new Request("http://localhost:3000/login/cellphone");
        request.setMethod(HttpConstant.Method.POST);
        Map<String, Object> map = new HashMap<>();
        map.put("phone","15815115306");
        map.put("password","abc123456");
        request.setRequestBody(HttpRequestBody.form(map,"utf-8"));
        Spider.create(new GetSongList(playlistService,playlistSongService,songService,singerService))
                .addRequest(request)
                .addUrl("http://localhost:3000/top/playlist?limit=20")
                .addPipeline(new ConsolePipeline())
                //开启5个线程抓取
                .thread(10)
                //启动爬虫
                .run();
        clearSong();
    }


    private void delAllAboutMusicTabel(){
        playlistService.remove(new QueryWrapper<>());
        playlistSongService.remove(new QueryWrapper<>());
        songService.remove(new QueryWrapper<Song>().eq("comment","0"));
        singerService.remove(new QueryWrapper<>());
    }


    public void updataSong(String ids){
        Spider.create(new UpdataSong(songService))
                .addUrl(ids)
                .addPipeline(new ConsolePipeline())
                //开启5个线程抓取
                .thread(10)
                //启动爬虫
                .run();
    }

    @ApiOperation("更新音乐路径")
    @GetMapping("/updataSong")
    public void getSongIds(){
        int i=0;
        StringBuilder musicids = new StringBuilder();
        List<Song> list = songService.list(new QueryWrapper<Song>().eq("comment","0"));
        for (Song song : list) {
            musicids.append("," + song.getId());
            if(200<=i++){
                i=0;
                musicids.deleteCharAt(0);
                updataSong("http://localhost:3000/song/url?id="+musicids);
                musicids=new StringBuilder();
            }
        }
        musicids.deleteCharAt(0);
        updataSong("http://localhost:3000/song/url?id="+musicids);
        clearSong();
    }

    private void clearSong(){
        songService.removeByIds(songService.list(new QueryWrapper<Song>().isNull("song_url")));
    }

}
