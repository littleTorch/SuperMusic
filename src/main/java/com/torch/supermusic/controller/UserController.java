package com.torch.supermusic.controller;


import com.torch.supermusic.service.IUserService;
import com.torch.supermusic.util.argEntity.Register;
import com.torch.supermusic.util.result.ResultUtils;
import com.torch.supermusic.util.result.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
