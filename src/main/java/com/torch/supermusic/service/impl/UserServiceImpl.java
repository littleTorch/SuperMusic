package com.torch.supermusic.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.supermusic.entity.Authority;
import com.torch.supermusic.entity.Role;
import com.torch.supermusic.entity.User;
import com.torch.supermusic.entity.UserRole;
import com.torch.supermusic.mapper.UserMapper;
import com.torch.supermusic.service.IRoleService;
import com.torch.supermusic.service.IUserRoleService;
import com.torch.supermusic.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.supermusic.util.RedisUtil;
import com.torch.supermusic.util.SendEmail;
import com.torch.supermusic.util.argEntity.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Torch
 * @since 2021-12-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername::::" + username);
        //先在redis中查找
        User user = (User) redisUtil.get(username);
        if (user==null){
            //如果没有就去数据库查找
            user = getOne(new QueryWrapper<User>().eq("username", username));
            if (user == null) {
                throw new UsernameNotFoundException("用户不存在");
            }
            //根据用户id查找权限id
            ArrayList ids = new ArrayList<>();
            for (UserRole userRole : userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", user.getId()))) {
                ids.add(userRole.getRoleId());
            }
            //根据权限id查找权限，并保存到用户的Role属性里
            List<Role> roles = roleService.listByIds(ids);
            user.setRoles(roles);
            //写入权限
            List<GrantedAuthority> list=new ArrayList<>();
            for (Role role : roles) {
                list.add(new Authority(role.getName()));
            }
            user.setAuthorities(list);
            //保存到redis中
            redisUtil.set(username,user,24*60*60);
        }
        System.out.println(user);
        for (GrantedAuthority authority : user.getAuthorities()) {
            System.out.println("权限："+authority.getAuthority());
        }
        return user;
    }

    @Override
    public String sendmail(String mail) {
//        判断是否在一分钟内发送过
        Object o = redisUtil.get(mail);
        if (o == null) {
            int code = RandomUtil.randomInt(100000, 999999);
            redisUtil.set(mail + "code", code, 10 * 60);
            redisUtil.set(mail, new Date(), 60);
            StringBuilder text = new StringBuilder();
            text.append("<html><head></head>");
            text.append("<body><h1>验证码：</h1>");
            text.append("<p>" + code + "</p>");
            text.append("<p>验证码有效时间为10分钟</p>");
            text.append("<img src='https://s2.loli.net/2021/12/09/tmnSozJ4vaPAcHu.png'/></body>");
            text.append("</html>");
            return sendEmail.sendComplexEmail(mail, text.toString());
        } else {
            return "已发送，请1分钟后再试";
        }
    }

    @Override
    public String register(Register register) {
        int code = (int) redisUtil.get(register.getEmail() + "code");
//        判断验证码
        if (register.getCode().equals(code+"")) {
//            判断用户是否已存在
            if (count(new QueryWrapper<User>().eq("username", register.getUsername())) == 0) {
                User user = new User();
                user.setUsername(register.getUsername());
                user.setPassword(new BCryptPasswordEncoder().encode(register.getPassword()));
                user.setEmail(register.getEmail());
                save(user);
                UserRole userRole = new UserRole();
                userRole.setUserId(getOne(new QueryWrapper<User>().eq("username", user.getUsername())).getId());
                userRole.setRoleId(1);
                userRoleService.save(userRole);
                return "注册成功";
            } else {
                return "用户名已存在";
            }
        } else {
            return "验证码错误";
        }
    }

    @Override
    public boolean updateOne(User user) {
        Boolean flag=true;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("user_id",user.getId());
            userRoleService.removeByMap(map);
            updateById(user);
            for (Role role : user.getRoles()) {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(role.getId());
                userRoleService.save(userRole);
            }
        } catch (Exception e) {
            flag=false;
            e.printStackTrace();
        } finally {
        }
        return flag;
    }
}
