package com.torch.supermusic.filter;

import cn.hutool.json.JSONUtil;
import com.torch.supermusic.entity.User;
import com.torch.supermusic.service.impl.UserServiceImpl;
import com.torch.supermusic.util.JwtTokenUtil;
import com.torch.supermusic.util.RedisUtil;
import com.torch.supermusic.util.RequestUtils;
import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("===>请求路径："+request.getRequestURI());
        String token = request.getHeader( "token" );
        String method = request.getMethod();
        System.out.println("===>请求方法："+method);

        long incr = redisUtil.setIncr("ip：" + RequestUtils.getIPAddress(request), 1);
        if (incr>300){
            redisUtil.expire("ip：" + RequestUtils.getIPAddress(request),3, TimeUnit.MINUTES);
            returnJson(response,"您的访问过多，请停止访问，3分钟后再试！");
            return;
        }
//        解析前端传来的token
        String username = jwtTokenUtil.getUsernameFromToken(token);
//        判断token里用户名是否为空，以及系统有没有用户权限
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                根据用户名获取用户详情
                User userDetails = this.userDetailsService.loadUserByUsername(username);
//                判断token与用户是否匹配
                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    System.out.println("token验证成功");
                    System.out.println(token);
//                  设置为已登录
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        chain.doFilter(request, response);
    }

    public void returnJson(HttpServletResponse response, String object) {
        try {
            /** 设置contenttype为json ***/
            response.setContentType("application/json");
            /** 设置编码为utf-8 **/
            response.setCharacterEncoding("UTF-8");
            /** 将需要返回的对象转换为json ***/
            String str = JSONUtil.toJsonStr(object);
            /** 获取字符输出流 **/
            PrintWriter writer = response.getWriter();
            /** 写入json **/
            writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}