package com.torch.supermusic.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;

public class MybatisPlusAutoGenerator {

    public static void main(String[] args) {

        String username="root";
        String password="123456";
        String url="jdbc:mysql://127.0.0.1:3306/supermusic?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=GMT%2B8";

        ArrayList<String> tables = new ArrayList<>();
        tables.add("permission");
        tables.add("playlist");
        tables.add("playlist_song");
        tables.add("role");
        tables.add("role_permission");
        tables.add("singer");
        tables.add("song");
        tables.add("user");
        tables.add("user_role");


        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("Torch") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\ideawork\\project\\supermusic\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.torch.supermusic") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\ideawork\\project\\supermusic\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)// 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                            .entityBuilder()    //修改entity类配置
                            .enableLombok()        //开启lombok
                            .enableTableFieldAnnotation()   //开启字段注释
                            .idType(IdType.AUTO)       //主键类型
                            .mapperBuilder()
                            .enableMapperAnnotation()
                            .enableBaseColumnList()
                            .enableBaseResultMap();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
