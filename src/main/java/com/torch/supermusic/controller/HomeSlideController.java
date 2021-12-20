package com.torch.supermusic.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import com.torch.supermusic.util.result.ResultUtils;
import com.torch.supermusic.util.result.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Torch
 * @since 2021-12-14
 */
@RestController
@RequestMapping("/home-slide")
public class HomeSlideController {

    private String IMG_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\home_slide";

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("上传图片")
    @PostMapping("/uploadImg")
    public ResultVo uploadImg(@RequestParam("file") MultipartFile img) throws Exception {
        //判断用户是否上传了文件
        if (!img.isEmpty()) {
            //文件上传的地址
            //用于查看路径是否正确
            System.out.println(IMG_PATH);
            //获取文件的名称
            String fileName = img.getOriginalFilename();
            //限制文件上传的类型
            String contentType = img.getContentType();
            if ("image/jpeg".equals(contentType) || "image/jpg".equals(contentType)) {
                //防止文件名重复加上UUID
                File file = new File(IMG_PATH, UUID.randomUUID(false) + "-" + fileName);
                //完成文件的上传
                img.transferTo(file);
                return ResultUtils.success("上传成功！");
            } else {
                return ResultUtils.error("文件类型错误，只能上传jpg/jpeg格式文件！");
            }
        }
        return ResultUtils.error("文件为空！");
    }

    @ApiOperation("获取图片列表")
    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/getImgsUrl")
    public ResultVo getImg() {
        ArrayList<String> files = new ArrayList<>();
        for (File l : FileUtil.ls(IMG_PATH)) {
            System.out.println(l.getName());
            files.add(l.getName());
        }
        return ResultUtils.success("获取成功！", files);
    }

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("获取图片")
    @GetMapping("/showImg")
    public void ShowImg(String imgUrl, HttpServletResponse response) {

        //这里是存放图片的文件夹地址
        response.setContentType("image/jpeg");
        File file = null;
        try {
            file = new File(IMG_PATH + "\\" + imgUrl);
            BufferedImage image = ImageIO.read(file);
            //得到向客户端输出二进制数据的对象
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
            if (out != null) {
                out.close();
            }
        } catch (Exception e) {
            System.out.println("系统找不到图像文件：" + IMG_PATH + "\\" + imgUrl);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("删除图片")
    @DeleteMapping("/del")
    public ResultVo del(@RequestBody String name) {
        String str = name.replace("\"", "");
        System.out.println(IMG_PATH + "\\" + str);
        File file = new File(IMG_PATH + "\\" + str);
        return file.delete() ? ResultUtils.success("删除成功！") : ResultUtils.error("删除失败！");
    }
}