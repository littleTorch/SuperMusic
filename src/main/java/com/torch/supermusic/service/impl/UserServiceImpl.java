package com.torch.supermusic.service.impl;

import com.torch.supermusic.entity.User;
import com.torch.supermusic.mapper.UserMapper;
import com.torch.supermusic.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Torch
 * @since 2021-12-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
