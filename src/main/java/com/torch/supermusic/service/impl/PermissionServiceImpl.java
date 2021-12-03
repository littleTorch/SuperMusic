package com.torch.supermusic.service.impl;

import com.torch.supermusic.entity.Permission;
import com.torch.supermusic.mapper.PermissionMapper;
import com.torch.supermusic.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
