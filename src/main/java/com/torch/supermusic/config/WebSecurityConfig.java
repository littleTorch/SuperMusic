package com.torch.supermusic.config;

import com.torch.supermusic.filter.JwtTokenFilter;
import com.torch.supermusic.service.IUserService;
import com.torch.supermusic.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @www.codesheep.cn
 * 20190312
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Bean
    public JwtTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( userService ).passwordEncoder( new BCryptPasswordEncoder() );
    }

    @Override
    protected void configure( HttpSecurity httpSecurity ) throws Exception {

        httpSecurity.csrf().disable()//关闭CSRF
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//禁用session
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.xlsx",
                        "/webjars/**",
                        "/v2/**",
                        "/swagger-resources/**"
                ).permitAll()//资源监测类接口放行
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/login", "/login/image", "/my/icon","/foodDate/pic", "/swagger-ui/**","/getmusic").permitAll()//登录,注册等无需身份的接口放行，其他接口全部接受验证
                //跨域请求会先进行一次OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                开发时先全部放行
                .antMatchers(HttpMethod.POST).permitAll()
                .antMatchers(HttpMethod.PUT).permitAll()
                .antMatchers(HttpMethod.DELETE).permitAll()
                .antMatchers(HttpMethod.GET).permitAll();

        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
    }

}