package com.woowacourse.momo.participant.exception;

import com.woowacourse.momo.global.exception.exception.CustomMomoException;

public class ParticipantException extends CustomMomoException {

    public ParticipantException(ParticipantExceptionMessage message) {
        super(message);
    }
}
