package com.torch.supermusic.controller;

import com.torch.supermusic.entity.User;
import com.torch.supermusic.util.result.ResultUtils;
import com.torch.supermusic.util.result.ResultVo;
import com.torch.supermusic.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
*登录与注册相关的类
**/

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IAuthService authService;

    @PostMapping()
    public ResultVo login(@RequestBody User user){
        System.out.println("*****************");
        System.out.println(user);
        return new ResultUtils().success("登录成功！",authService.login(user.getUsername(), user.getPassword()));
    }

}
