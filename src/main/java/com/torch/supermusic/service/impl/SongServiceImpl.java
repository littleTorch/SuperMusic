package com.torch.supermusic.service.impl;

import com.torch.supermusic.entity.Song;
import com.torch.supermusic.mapper.SongMapper;
import com.torch.supermusic.service.ISongService;
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
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements ISongService {

}
