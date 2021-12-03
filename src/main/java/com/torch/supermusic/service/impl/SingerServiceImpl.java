package com.torch.supermusic.service.impl;

import com.torch.supermusic.entity.Singer;
import com.torch.supermusic.mapper.SingerMapper;
import com.torch.supermusic.service.ISingerService;
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
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements ISingerService {

}
