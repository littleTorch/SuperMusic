package com.torch.supermusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.supermusic.entity.Role;
import com.torch.supermusic.entity.User;
import com.torch.supermusic.entity.UserRole;
import com.torch.supermusic.mapper.UserMapper;
import com.torch.supermusic.service.IRoleService;
import com.torch.supermusic.service.IUserRoleService;
import com.torch.supermusic.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getOne(new QueryWrapper<User>().eq("username",username));
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<UserRole> user_id = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
        ArrayList ids = new ArrayList<>();
        for (UserRole userRole : user_id) {
            ids.add(userRole.getId());
        }
        user.setRoles(roleService.listByIds(ids));
        return user;
    }
}
