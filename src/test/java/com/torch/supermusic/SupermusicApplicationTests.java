package com.torch.supermusic;

import com.torch.supermusic.controller.GetMusicController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SupermusicApplicationTests {

    @Autowired
    GetMusicController getMusicController;

    @Test
    void contextLoads() {
        getMusicController.delAllAboutMusicTabel();
    }

}
