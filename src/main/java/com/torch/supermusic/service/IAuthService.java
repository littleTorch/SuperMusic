package com.torch.supermusic.service;

import com.torch.supermusic.entity.User;

public interface IAuthService {
    User register( User userToAdd );
    String login( String username, String password );
}
