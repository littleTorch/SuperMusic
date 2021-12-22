package com.torch.supermusic.controller;


import cn.hutool.core.collection.IterUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.supermusic.entity.Role;
import com.torch.supermusic.entity.User;
import com.torch.supermusic.entity.UserRole;
import com.torch.supermusic.service.IRoleService;
import com.torch.supermusic.service.IUserRoleService;
import com.torch.supermusic.service.IUserService;
import com.torch.supermusic.util.argEntity.Register;
import com.torch.supermusic.util.argEntity.SelectAndPage;
import com.torch.supermusic.util.result.ResultUtils;
import com.torch.supermusic.util.result.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
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

    @ApiOperation("获取邮件验证码")
    @GetMapping("/getcode")
    public ResultVo getCode(String mail){
        return ResultUtils.success(userService.sendmail(mail));
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public ResultVo register(@RequestBody Register register){
        System.out.println(register);
        return ResultUtils.success(userService.register(register));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("根据用户名查询用户分页")
    @GetMapping("/page")
    public ResultVo PageSongAndSinger(SelectAndPage selectAndPage){
        Page<User> page = userService.page(new Page<User>(selectAndPage.getCurrentPage(), selectAndPage.getPageSize()), new QueryWrapper<User>().like("username", selectAndPage.getArg()));
        for (User user : page.getRecords()) {
            List<UserRole> userRoles = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
            if (userRoles.size()!=0) {
                ArrayList<Integer> roleIds = new ArrayList<>();
                for (UserRole userRole : userRoles) {
                    roleIds.add(userRole.getRoleId());
                }
                user.setRoles(roleService.listByIds(roleIds));
            }
        }
        return ResultUtils.success("查询成功", page);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("更新用户")
    @PutMapping("/updata")
    public ResultVo updata(@RequestBody User user){
        if (!(user.getPassword()).equals(userService.getById(user.getId()).getPassword())){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        return userService.updateById(user)? ResultUtils.success("修改成功"):ResultUtils.error("修改失败");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("添加用户")
    @PostMapping("/add")
    public ResultVo add(@RequestBody User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);
        //将userid和roleid交给用户角色类去添加数据
        //待写！！！！！！！！！！！！
        return false? ResultUtils.success("添加成功"):ResultUtils.error("添加失败");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("删除用户")
    @DeleteMapping(value = "/dels")
    public ResultVo delete(@RequestBody String[] ids){
        for (String id : ids) {
            userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id", id));
        }
        return userService.removeByIds(Arrays.asList(ids)) ? ResultUtils.success("删除成功") : ResultUtils.success("删除失败") ;
    }
}