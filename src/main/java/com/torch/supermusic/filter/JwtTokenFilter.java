package com.torch.supermusic.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.torch.supermusic.handler.LoginFailureHandler;
import com.torch.supermusic.handler.TokenException;
import com.torch.supermusic.service.impl.UserServiceImpl;
import com.torch.supermusic.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        System.out.println(request.getRequestURI());
//        String token = request.getHeader( "token" );
//        String method = request.getMethod();
//        System.out.println(method);
//        System.out.println(SecurityContextHolder.getContext().getAuthentication());
////        解析前端传来的token
//        String username = jwtTokenUtil.getUsernameFromToken(token);
////        判断用户名是否为空，以及系统有没有用户权限
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////                有token但没有保存到系统里
//                System.out.println("用户验证成功");
////                根据用户名获取用户详情
//                User userDetails = this.userDetailsService.loadUserByUsername(username);
////                判断token与用户是否匹配
//                if (jwtTokenUtil.validateToken(token, userDetails)) {
//                    System.out.println("token验证成功");
//                    System.out.println(token);
////                  设置为已登录
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
//                            request));
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            }
//        chain.doFilter(request, response);
//    }
        String url = request.getRequestURI();
        System.out.println(url);
            //验证token,验证码请求不需要验证token
            if(!url.startsWith("/swagger-ui") && !url.startsWith("/getmusic")){
                try{
                    validateToken(request);
                }catch (AuthenticationException e){
                    loginFailureHandler.onAuthenticationFailure(request,response,e);
                    return;
                }
            }
        // 放行请求
        chain.doFilter(request, response);
    }

    //验证token
    private void validateToken(HttpServletRequest request){
        //获取前端传来的token
        String token = request.getHeader("token");
        //解析token，获取用户名
        String username = jwtTokenUtil.getUsernameFromToken(token);
//      登录请求跳过token认证
        if (request.getRequestURI().startsWith("/login")){
            return;
        }
        //如果token或者用户名为空的话，不能通过认证
        if(StringUtils.isBlank(token) || StringUtils.isBlank(username)){
            throw new TokenException("token验证失败!");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails == null){
            throw new TokenException("token验证失败!");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        //设置为已登录
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}