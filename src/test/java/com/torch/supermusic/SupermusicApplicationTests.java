package com.torch.supermusic;

import com.torch.supermusic.controller.GetMusicController;
import com.torch.supermusic.entity.User;
import com.torch.supermusic.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
class SupermusicApplicationTests {

    @Autowired
    IUserService userService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setId(2);
        user.setUsername("admin");
        user.setPassword(new BCryptPasswordEncoder().encode("123"));
        userService.save(user);
    }

}
