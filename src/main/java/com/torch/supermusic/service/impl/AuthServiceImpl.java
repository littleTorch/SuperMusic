package com.torch.supermusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.supermusic.entity.User;
import com.torch.supermusic.service.IAuthService;
import com.torch.supermusic.service.IUserService;
import com.torch.supermusic.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IUserService userService;

    // 登录
    @Override
    public String login( String username, String password ) {

        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken( username, password );

        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userDetailsService.loadUserByUsername( username );
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    // 注册
    @Override
    public User register( User userToAdd ) {

        final String username = userToAdd.getUsername();
        if( userService.getOne(new QueryWrapper<User>().eq("username",username))!=null ) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword( encoder.encode(rawPassword) );
        userService.save(userToAdd);
        return userToAdd;
    }
}