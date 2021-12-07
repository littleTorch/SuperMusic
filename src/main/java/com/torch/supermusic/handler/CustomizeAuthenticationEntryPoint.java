package com.torch.supermusic.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.torch.supermusic.util.result.CodeStatus;
import com.torch.supermusic.util.result.ResultUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 匿名用户访问无权限资源时的处理器
 */
@Component("customizeAuthenticationEntryPoint")
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("text/json;charset=utf-8");
        ServletOutputStream out = httpServletResponse.getOutputStream();
        String res = JSON.toJSONString(ResultUtils.error("匿名用户无权限访问！", CodeStatus.NO_LOGIN, null), SerializerFeature.DisableCircularReferenceDetect);
        out.write(res.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
