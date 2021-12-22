package com.torch.supermusic.service.impl;

import com.torch.supermusic.entity.Role;
import com.torch.supermusic.entity.UserRole;
import com.torch.supermusic.mapper.UserRoleMapper;
import com.torch.supermusic.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {


    @Override
    public Boolean saveAll(Integer id, List<Role> roles) {
        Boolean flag=true;
        try {
            for (Role role : roles) {
                UserRole userRole = new UserRole();
                userRole.setUserId(id);
                userRole.setRoleId(role.getId());
                save(userRole);
            }
        } catch (Exception e) {
            flag=false;
            e.printStackTrace();
        } finally {
            return flag;
        }
    }
}
