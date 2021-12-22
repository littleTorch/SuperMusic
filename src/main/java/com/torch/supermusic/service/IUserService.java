package com.torch.supermusic.service;

import com.torch.supermusic.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.supermusic.util.argEntity.Register;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Torch
 * @since 2021-12-03
 */
public interface IUserService extends IService<User> {

    String sendmail(String mail);

    String register(Register register);

    boolean updateOne(User user);
}
