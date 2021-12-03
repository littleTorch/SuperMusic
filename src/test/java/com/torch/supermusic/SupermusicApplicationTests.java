package com.torch.supermusic;

//import com.torch.supermusic.entity.Playlist;
//import com.torch.supermusic.service.impl.PlaylistServiceImpl;
import com.torch.supermusic.climbing.GetMusic;
import com.torch.supermusic.entity.Playlist;
import com.torch.supermusic.service.IPlaylistService;
import com.torch.supermusic.service.impl.PlaylistServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SupermusicApplicationTests {

    @Autowired
    private IPlaylistService playlistService;

    @Test
    void contextLoads() {

//        List<Playlist> list = playlistService.list();
//        for (Playlist playlist : list) {
//            System.out.println(playlist);
//        }
    }

}
