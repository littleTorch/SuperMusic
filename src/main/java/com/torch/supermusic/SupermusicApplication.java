package com.torch.supermusic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

//开启swagger3
@EnableOpenApi
//开启定时任务
@EnableScheduling
@SpringBootApplication
@MapperScan("com.torch.supermusic.mapper")
public class SupermusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupermusicApplication.class, args);
    }

}
