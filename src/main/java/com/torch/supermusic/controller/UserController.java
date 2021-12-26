package com.torch.supermusic.controller;


import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.supermusic.entity.Role;
import com.torch.supermusic.entity.User;
import com.torch.supermusic.entity.UserRole;
import com.torch.supermusic.service.IRoleService;
import com.torch.supermusic.service.IUserRoleService;
import com.torch.supermusic.service.IUserService;
import com.torch.supermusic.util.JwtTokenUtil;
import com.torch.supermusic.util.RedisUtil;
import com.torch.supermusic.util.argEntity.Register;
import com.torch.supermusic.util.argEntity.SelectAndPage;
import com.torch.supermusic.util.result.ResultUtils;
import com.torch.supermusic.util.result.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Torch
 * @since 2021-12-03
 */
@Api(tags = {"用户类"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisUtil redisUtil;

    private String IMG_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\userIcon";


    @ApiOperation("获取邮件验证码")
    @GetMapping("/getcode")
    public ResultVo getCode(String mail) {
        return ResultUtils.success(userService.sendmail(mail));
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public ResultVo register(@RequestBody Register register) {
        return ResultUtils.success(userService.register(register));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("根据用户名查询用户分页")
    @GetMapping("/page")
    public ResultVo PageSongAndSinger(SelectAndPage selectAndPage) {
        Page<User> page = userService.page(new Page<User>(selectAndPage.getCurrentPage(), selectAndPage.getPageSize()), new QueryWrapper<User>().like("username", selectAndPage.getArg()));
        for (User user : page.getRecords()) {
            List<UserRole> userRoles = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
            if (userRoles.size() != 0) {
                ArrayList<Integer> roleIds = new ArrayList<>();
                for (UserRole userRole : userRoles) {
                    roleIds.add(userRole.getRoleId());
                }
                user.setRoles(roleService.listByIds(roleIds));
            }
        }
        return ResultUtils.success("查询成功", page);
    }

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("更新用户")
    @PutMapping("/updata")
    public ResultVo updata(@RequestBody User user) {
        if (user.getPassword() != null && !(user.getPassword()).equals(userService.getById(user.getId()).getPassword())) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        if (user.getPassword() != null && !userService.getById(user).getUsername().equals(user.getUsername())) {
            if (userService.count(new QueryWrapper<User>().eq("username", user.getUsername())) == 1) {
                return ResultUtils.error("用户名已存在！");
            }
        }
        if (redisUtil.hasKey(user.getUsername())){
            redisUtil.del(user.getUsername());
        }
        return userService.updateOne(user) ? ResultUtils.success("修改成功") : ResultUtils.error("修改失败");
    }

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("更新密码")
    @PutMapping("/updataPassword")
    public ResultVo updataPas(@RequestBody SelectAndPage selectAndPage) {
        User user = userService.getById(selectAndPage.getArg());
        if (new BCryptPasswordEncoder().matches(selectAndPage.getArg(),user.getPassword())){
            return ResultUtils.error("旧密码错误");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(selectAndPage.getArg2()));
        if (redisUtil.hasKey(user.getUsername())){
            redisUtil.del(user.getUsername());
        }
        return userService.updateOne(user) ? ResultUtils.success("修改成功") : ResultUtils.error("修改失败");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("添加用户")
    @PostMapping("/add")
    public ResultVo add(@RequestBody User user) {
        if (userService.count(new QueryWrapper<User>().eq("username", user.getUsername())) >= 1) {
            return ResultUtils.error("用户名已存在！");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        //将userid和roleid交给用户角色类去添加数据
        return userService.save(user) && userRoleService.saveAll(user.getId(), user.getRoles()) ? ResultUtils.success("添加成功") : ResultUtils.error("添加失败");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("删除用户")
    @DeleteMapping(value = "/dels")
    public ResultVo delete(@RequestBody String[] ids) {
        for (String id : ids) {
            redisUtil.del(userService.getById(id).getUsername());
            userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id", id));
        }
        return userService.removeByIds(Arrays.asList(ids)) ? ResultUtils.success("删除成功") : ResultUtils.success("删除失败");
    }

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("token查询用户")
    @GetMapping()
    public ResultVo tokenByUser(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        return ResultUtils.success("获取成功！", user);
    }

    @PreAuthorize("hasRole('NORMAL')")
    @ApiOperation("上传图片")
    @PostMapping("/uploadImg")
    public ResultVo uploadImg(@RequestParam("file") MultipartFile img,@RequestParam("userId") Integer userId) throws Exception {
        //判断用户是否上传了文件
        if (!img.isEmpty()) {
            //文件上传的地址
            //限制文件上传的类型
            String contentType = img.getContentType();
            if ("image/jpeg".equals(contentType) || "image/jpg".equals(contentType)) {
                //防止文件名重复加上UUID
                File file = new File(IMG_PATH,userId+".jpg");
                //完成文件的上传
                img.transferTo(file);
                return ResultUtils.success("上传成功！");
            } else {
                return ResultUtils.error("文件类型错误，只能上传jpg/jpeg格式文件！");
            }
        }
        return ResultUtils.error("文件为空！");
    }

    @ApiOperation("获取图片")
    @GetMapping("/showImg")
    public void ShowImg(String userId, HttpServletResponse response) {

        //这里是存放图片的文件夹地址
        response.setContentType("image/jpeg");
        File file = null;
        try {
            file = new File(IMG_PATH + "\\" + userId+".jpg");
            BufferedImage image = ImageIO.read(file);
            //得到向客户端输出二进制数据的对象
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
            if (out != null) {
                out.close();
            }
        } catch (Exception e) {
            System.out.println("系统找不到图像文件：" + IMG_PATH + "\\" + userId+".jpg");
        }
    }
}