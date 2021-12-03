package com.torch.supermusic.service.impl;

import com.torch.supermusic.entity.Playlist;
import com.torch.supermusic.mapper.PlaylistMapper;
import com.torch.supermusic.service.IPlaylistService;
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
public class PlaylistServiceImpl extends ServiceImpl<PlaylistMapper, Playlist> implements IPlaylistService {

}
