package com.woowacourse.momo.participant.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import static com.woowacourse.momo.participant.exception.ParticipantExceptionMessage.GROUP_ALREADY_CLOSED;
import static com.woowacourse.momo.participant.exception.ParticipantExceptionMessage.HOST_CANNOT_LEAVE_OWN_GROUP;
import static com.woowacourse.momo.participant.exception.ParticipantExceptionMessage.HOST_CANNOT_PARTICIPATE_OWN_GROUP;
import static com.woowacourse.momo.participant.exception.ParticipantExceptionMessage.MEMBER_CANNOT_PARTICIPATE_IN_CLOSED_GROUP;
import static com.woowacourse.momo.participant.exception.ParticipantExceptionMessage.MEMBER_IS_NOT_PARTICIPATED_IN;
import static com.woowacourse.momo.participant.exception.ParticipantExceptionMessage.PARTICIPANT_CANNOT_PARTICIPATE_IN_ALREADY_PARTICIPATED_GROUP;
import static com.woowacourse.momo.participant.exception.ParticipantExceptionMessage.PARTICIPANT_LEAVE_DEADLINE;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;

import com.woowacourse.momo.global.exception.exception.ExceptionCode;
import com.woowacourse.momo.global.exception.exception.ExceptionMessage;

public enum ParticipantExceptionCode implements ExceptionCode {

    PARTICIPANT_ERROR_001(BAD_REQUEST, HOST_CANNOT_PARTICIPATE_OWN_GROUP),
    PARTICIPANT_ERROR_002(BAD_REQUEST, PARTICIPANT_CANNOT_PARTICIPATE_IN_ALREADY_PARTICIPATED_GROUP),
    PARTICIPANT_ERROR_003(BAD_REQUEST, MEMBER_CANNOT_PARTICIPATE_IN_CLOSED_GROUP),
    PARTICIPANT_ERROR_004(BAD_REQUEST, HOST_CANNOT_LEAVE_OWN_GROUP),
    PARTICIPANT_ERROR_005(BAD_REQUEST, MEMBER_IS_NOT_PARTICIPATED_IN),
    PARTICIPANT_ERROR_006(BAD_REQUEST, PARTICIPANT_LEAVE_DEADLINE),
    PARTICIPANT_ERROR_007(BAD_REQUEST, GROUP_ALREADY_CLOSED),
    ;

    private final HttpStatus status;
    private final List<ExceptionMessage> messages;

    ParticipantExceptionCode(HttpStatus status, ExceptionMessage... messages) {
        this.status = status;
        this.messages = List.of(messages);
    }

    public static ParticipantExceptionCode from(ExceptionMessage message) {
        return Stream.of(values())
                .filter(code -> code.contains(message))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    private boolean contains(ExceptionMessage message) {
        return messages.contains(message);
    }

    public String getCode() {
        return name();
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
