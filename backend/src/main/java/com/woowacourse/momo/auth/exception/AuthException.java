package com.woowacourse.momo.auth.exception;

import com.woowacourse.momo.global.exception.exception.CustomMomoException;

public class AuthException extends CustomMomoException {

    public AuthException(AuthExceptionMessage message) {
        super(message);
    }
}
