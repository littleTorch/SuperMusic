package com.torch.supermusic.climbing;

import com.alibaba.fastjson.JSON;
import com.torch.supermusic.climbing.josnpojo.MusicList;
import com.torch.supermusic.climbing.josnpojo.MusicUrl;
import com.torch.supermusic.climbing.josnpojo.PlayList;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.*;

public class GetMusic implements PageProcessor {

    static String URL="http://localhost:3000/";


    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000).setTimeOut(10000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        String musicids="33894312";
        System.out.println("进入**************************************");
        String thisUrl=page.getRequest().getUrl();
        System.out.println(thisUrl);
//        获取排行榜列表
        if (thisUrl.contains("toplist")){
        List<String> alllist = page.getJson().jsonPath("$.list[*]").all();
//        取出需要的数据
//        List<PlayList> playList = new ArrayList<>();
        for (String s : alllist) {
            PlayList parse =JSON.parseObject(s,PlayList.class);
            page.addTargetRequest(URL+"playlist/track/all?id="+parse.getId());
//            playList.add(parse);
//            将歌单写入数据库
            System.out.println(parse);
            break;
        }
        }
//      获取歌单里的歌曲列表
        if (thisUrl.contains("playlist/track/all")){
            List<String> allmusic = page.getJson().jsonPath("$.songs[*]").all();
            for (String s : allmusic) {
                MusicList musicList = JSON.parseObject(s, MusicList.class);
                System.out.println(musicList);
                musicids+=","+musicList.getId();
            }
            page.addTargetRequest(URL+"song/url?id="+musicids);
        }
        //      获取歌曲url
        if (thisUrl.contains("song/url")){
            List<String> allMusicUrl = page.getJson().jsonPath("$.data[*]").all();
            for (String s : allMusicUrl) {
                MusicUrl musicUrl = JSON.parseObject(s, MusicUrl.class);
                System.out.println(musicUrl);
            }
        }


    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Request request = new Request(URL+"login/cellphone");
        request.setMethod(HttpConstant.Method.POST);
        Map<String, Object> map = new HashMap<>();
        map.put("phone","15815115306");
        map.put("password","abc123456");
        request.setRequestBody(HttpRequestBody.form(map,"utf-8"));
        Spider.create(new GetMusic())
                .addRequest(request)
                .addUrl(URL+"toplist")
                .addPipeline(new JsonFilePipeline("D:\\test"))
                //开启1个线程抓取
                .thread(1)
                //启动爬虫
                .run();
    }
}
