package com.torch.supermusic.util.climbing;

import cn.hutool.core.util.ReUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.supermusic.util.climbing.josnpojo.Artist;
import com.torch.supermusic.util.climbing.josnpojo.MusicList;
import com.torch.supermusic.util.climbing.josnpojo.MusicUrl;
import com.torch.supermusic.util.climbing.josnpojo.PlayList;

import com.torch.supermusic.entity.Playlist;

import com.torch.supermusic.entity.PlaylistSong;
import com.torch.supermusic.entity.Singer;
import com.torch.supermusic.entity.Song;
import com.torch.supermusic.service.IPlaylistService;
import com.torch.supermusic.service.IPlaylistSongService;
import com.torch.supermusic.service.ISingerService;
import com.torch.supermusic.service.ISongService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import java.util.*;

public class GetMusic implements PageProcessor {

    public GetMusic(IPlaylistService playlistService, IPlaylistSongService playlistSongService, ISongService songService, ISingerService singerService) {
        this.playlistService = playlistService;
        this.playlistSongService = playlistSongService;
        this.songService = songService;
        this.singerService = singerService;
    }

    static String URL="http://localhost:3000/";
    private IPlaylistService playlistService;
    private IPlaylistSongService playlistSongService;
    private ISongService songService;
    private ISingerService singerService;

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(0).setTimeOut(10000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        StringBuilder musicids=null;
        String thisUrl=page.getRequest().getUrl();
        String thisId = ReUtil.get("\\d{1,15}$", thisUrl, 0);
        System.out.println(thisUrl);
//        获取排行榜列表
        if (thisUrl.contains("toplist")){
        List<String> alllist = page.getJson().jsonPath("$.list[*]").all();
//        取出需要的数据
        for (String s : alllist) {
            PlayList parse =JSON.parseObject(s,PlayList.class);
            page.addTargetRequest(URL+"playlist/track/all?id="+parse.getId());
            System.out.println(parse);
//            将歌单写入数据库
            Playlist playlist = new Playlist();
            playlist.setId(parse.getId());
            playlist.setPlaylistName(parse.getName());
            playlist.setIcon(parse.getCoverImgUrl());
            playlist.setPlaylistComment(parse.getDescription());
            playlist.setPlaylistType(1);
            playlistService.save(playlist);
        }
        }
//      获取歌单里的歌曲列表
        if (thisUrl.contains("playlist/track/all")){
            musicids=new StringBuilder();
            List<String> allmusic = page.getJson().jsonPath("$.songs[*]").all();
            for (String s : allmusic) {
                MusicList musicList = JSON.parseObject(s, MusicList.class);
                System.out.println(musicList);
                musicids.append(","+musicList.getId());
                //歌单歌曲表
                PlaylistSong playlistSong = new PlaylistSong();
                playlistSong.setPlaylistId(Long.parseLong(thisId));
                playlistSong.setSongId(musicList.getId());
                playlistSongService.save(playlistSong);
                //歌曲表
                Song song = new Song();
                song.setId(musicList.getId());
                song.setSingerId(musicList.getAr().get(0).getId());
                song.setName(musicList.getName());
                song.setPictureUrl(musicList.getAl().getPicUrl());
                songService.save(song);
                page.addTargetRequest(URL+"artist/detail?id="+song.getSingerId());
            }
            musicids.deleteCharAt(0);
            page.addTargetRequest(URL+"song/url?id="+musicids);
        }
        //获取歌曲url
        if (thisUrl.contains("song/url")){
            List<String> allMusicUrl = page.getJson().jsonPath("$.data[*]").all();
            for (String s : allMusicUrl) {
                MusicUrl musicUrl = JSON.parseObject(s, MusicUrl.class);
                System.out.println(musicUrl);
                Song song = new Song();
                song.setId(musicUrl.getId());
                song.setSongUrl(musicUrl.getUrl());
                if (song.getSongUrl()==null){
                    songService.removeById(song.getId());
                    playlistSongService.remove(new QueryWrapper<PlaylistSong>().eq("song_id",song.getId()));
                }else{
                    songService.updateById(song);
                }
            }
        }
        if (thisUrl.contains("artist/detail")){
            Artist artist = JSONUtil.toBean(page.getJson().jsonPath("$.data.artist").get(), Artist.class);
            Singer singer = new Singer();
            singer.setId(artist.getId());
            singer.setIcon(artist.getCover());
            singer.setProfile(artist.getBriefDesc());
            singer.setSingerName(artist.getName());
            singerService.save(singer);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

//    public static void main(String[] args) {
////        Request request = new Request(URL+"login/cellphone");
////        request.setMethod(HttpConstant.Method.POST);
////        Map<String, Object> map = new HashMap<>();
////        map.put("phone","15815115306");
////        map.put("password","abc123456");
////        request.setRequestBody(HttpRequestBody.form(map,"utf-8"));
//        Spider.create(new GetMusic())
////                .addRequest(request)
//                .addUrl(URL+"toplist")
//                .addPipeline(new ConsolePipeline())
//                //开启1个线程抓取
//                .thread(1)
//                //启动爬虫
//                .run();
//    }
}
