package com.woowacourse.momo.member.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import static com.woowacourse.momo.member.exception.MemberExceptionMessage.MEMBER_DELETED;
import static com.woowacourse.momo.member.exception.MemberExceptionMessage.MEMBER_DELETE_FAILED_BY_PROGRESSING_GROUP_EXIST;
import static com.woowacourse.momo.member.exception.MemberExceptionMessage.MEMBER_DOES_NOT_EXIST;
import static com.woowacourse.momo.member.exception.MemberExceptionMessage.PASSWORD_DOES_NOT_MATCH;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;

import com.woowacourse.momo.global.exception.exception.ExceptionCode;
import com.woowacourse.momo.global.exception.exception.ExceptionMessage;

public enum MemberExceptionCode implements ExceptionCode {

    MEMBER_ERROR_001(BAD_REQUEST, MEMBER_DOES_NOT_EXIST),
    MEMBER_ERROR_002(BAD_REQUEST, MEMBER_DELETED),
    MEMBER_ERROR_003(BAD_REQUEST, MEMBER_DELETE_FAILED_BY_PROGRESSING_GROUP_EXIST),
    MEMBER_ERROR_004(BAD_REQUEST, PASSWORD_DOES_NOT_MATCH),
    ;

    private final HttpStatus status;
    private final List<ExceptionMessage> messages;

    MemberExceptionCode(HttpStatus status, ExceptionMessage... messages) {
        this.status = status;
        this.messages = List.of(messages);
    }

    public static MemberExceptionCode from(ExceptionMessage message) {
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
