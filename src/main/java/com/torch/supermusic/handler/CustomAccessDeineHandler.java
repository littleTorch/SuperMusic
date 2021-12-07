package com.torch.supermusic.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.torch.supermusic.util.result.CodeStatus;
import com.torch.supermusic.util.result.ResultUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证用户访问无权限资源时的处理器
 */
@Component("customAccessDeineHandler")
public class CustomAccessDeineHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        response.setContentType("text/json;charset=utf-8");
        ServletOutputStream out = response.getOutputStream();
        String res = JSON.toJSONString(ResultUtils.error("无权限访问，请联系管理员！", CodeStatus.NO_AUTN, null), SerializerFeature.DisableCircularReferenceDetect);
        out.write(res.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
