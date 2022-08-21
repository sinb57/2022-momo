package com.woowacourse.momo.member.exception;

import com.woowacourse.momo.global.exception.exception.CustomMomoException;

public class MemberException extends CustomMomoException {

    public MemberException(MemberExceptionMessage message) {
        super(message);
    }
}
