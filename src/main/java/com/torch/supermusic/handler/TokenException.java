package com.torch.supermusic.handler;

import org.springframework.security.core.AuthenticationException;

/**
 * token异常处理类
 */
public class TokenException extends AuthenticationException {
    public TokenException(String msg) {
        super(msg);
    }
}
