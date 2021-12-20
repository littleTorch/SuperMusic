package com.torch.supermusic.util.climbing;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.supermusic.entity.PlaylistSong;
import com.torch.supermusic.entity.Song;
import com.torch.supermusic.service.ISongService;
import com.torch.supermusic.util.climbing.josnpojo.MusicUrl;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class UpdataSong implements PageProcessor {

    private ISongService songService;
    private Site site = Site.me().setRetryTimes(3).setSleepTime(0).setTimeOut(10000);

    public UpdataSong(ISongService songService) {
        this.songService = songService;
    }

    @Override
    public void process(Page page) {
        List<String> allMusicUrl = page.getJson().jsonPath("$.data[*]").all();
        for (String s : allMusicUrl) {
            MusicUrl musicUrl = JSON.parseObject(s, MusicUrl.class);
            System.out.println(musicUrl);
            Song song = new Song();
            song.setId(musicUrl.getId());
            song.setSongUrl(musicUrl.getUrl());
            if (song.getSongUrl()=="") {
                songService.removeById(song.getId());
            } else {
                songService.updateById(song);
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
