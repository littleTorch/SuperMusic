package com.torch.supermusic.service;

import com.torch.supermusic.entity.Role;
import com.torch.supermusic.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Torch
 * @since 2021-12-03
 */
public interface IUserRoleService extends IService<UserRole> {

    Boolean saveAll(Integer id, List<Role> roles);
}
