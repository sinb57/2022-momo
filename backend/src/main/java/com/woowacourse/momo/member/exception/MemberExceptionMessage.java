package com.woowacourse.momo.member.exception;

import com.woowacourse.momo.global.exception.exception.ExceptionMessage;

public enum MemberExceptionMessage implements ExceptionMessage {

    MEMBER_DOES_NOT_EXIST("회원이 존재하지 않습니다."),
    MEMBER_DELETED("탈퇴한 회원입니다."),
    MEMBER_DELETE_FAILED_BY_PROGRESSING_GROUP_EXIST("진행중인 모임이 있어 탈퇴할 수 없습니다."),
    PASSWORD_DOES_NOT_MATCH("비밀번호가 일치하지 않습니다."),
    ;

    private final String message;

    MemberExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
