package com.torch.supermusic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.supermusic.entity.Song;
import com.torch.supermusic.entity.User;

import com.torch.supermusic.service.ISongService;
import com.torch.supermusic.service.IUserService;
import com.torch.supermusic.util.SendEmail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


@SpringBootTest
class SupermusicApplicationTests {

    @Autowired
    IUserService userService;
    @Autowired
    SendEmail sendEmail;

    @Autowired
    ISongService songService;


    @Test
    void contextLoads() {
        //User user = new User();
        //user.setId(2);
        //user.setUsername("admin");
        //user.setPassword(new BCryptPasswordEncoder().encode("123"));
        //userService.save(user);
        songService.removeByIds(songService.list(new QueryWrapper<Song>().isNull("song_url")));
    }

    @Test
    void sendcomplexemail(){
        String to="";
        String code="";
        // 定义邮件内容
        StringBuilder text = new StringBuilder();
        text.append("<html><head></head>");
        text.append("<body><h1>验证码：</h1>");
        text.append("<p>"+code+"</p>");
        text.append("<img src='http://www.uugai.com/logoa/logo_img_sc.php'/></body>");
        text.append("</html>");
        sendEmail.sendComplexEmail(to,text.toString());
    }
}
