package com.torch.supermusic.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.torch.supermusic.entity.User;
import com.torch.supermusic.util.JwtTokenUtil;
import com.torch.supermusic.util.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 登录认证成功处理器
 * 登录成功要返回 json 和 token
 */
@Component("loginSuccessHandler")
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenUtil jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        //1.获取用户信息（即UserDetailsService返回的user）
        User user = (User) authentication.getPrincipal();
        //2.生成token
        String token = jwtUtils.generateToken(user);
        HashMap<String, Object> map = new HashMap<>();
        map.put("roles",user.getAuthorities());
        map.put("token",token);
//          返回token
        String res = JSONObject.toJSONString(ResultUtils.success("登录成功",map), SerializerFeature.DisableCircularReferenceDetect);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = httpServletResponse.getOutputStream();
        out.write(res.getBytes("UTF-8"));
        out.flush();
        out.close();
    }

}
