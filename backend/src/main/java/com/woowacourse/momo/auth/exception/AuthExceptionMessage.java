package com.woowacourse.momo.auth.exception;

import org.springframework.http.HttpStatus;

import com.woowacourse.momo.global.exception.exception.ExceptionMessage;

public enum AuthExceptionMessage implements ExceptionMessage {

    HOST_CANNOT_PARTICIPATE_OWN_GROUP("주최자는 자신의 모임에 참여할 수 없습니다."),
    PARTICIPANT_CANNOT_PARTICIPATE_IN_ALREADY_PARTICIPATED_GROUP("참여자는 본인이 참여한 모임에 재참여할 수 없습니다."),
    MEMBER_CANNOT_PARTICIPATE_IN_CLOSED_GROUP("마감된 모임에는 참여할 수 없습니다."),
    HOST_CANNOT_LEAVE_OWN_GROUP("주최자는 모임에 탈퇴할 수 없습니다."),
    MEMBER_IS_NOT_PARTICIPATED_IN("모임의 참여자가 아닙니다."),
    PARTICIPANT_LEAVE_DEADLINE("모집이 마감된 모임입니다."),
    GROUP_ALREADY_CLOSED("조기종료된 모임입니다."),


    AUTH_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED.value(), "AUTH_ERROR_001", "토큰의 유효기간이 만료되었습니다."),
    AUTH_INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(), "AUTH_ERROR_002", "토큰이 유효하지 않습니다."),
    AUTH_REQUIRED_LOGIN(HttpStatus.UNAUTHORIZED.value(), "AUTH_ERROR_003", "로그인이 필요합니다."),
    AUTH_DELETE_NO_HOST(HttpStatus.FORBIDDEN.value(), "AUTH_ERROR_004", "주최자가 아닌 사람은 모임을 수정하거나 삭제할 수 없습니다."),

    OAUTH_ACCESS_TOKEN_REQUEST_FAILED_BY_NON_2XX_STATUS(HttpStatus.INTERNAL_SERVER_ERROR.value(), "OAUTH_ERROR_001", "구글에 전송한 Access 토큰 요청이 실패했습니다."),
    OAUTH_ACCESS_TOKEN_REQUEST_FAILED_BY_NON_EXIST_BODY(HttpStatus.INTERNAL_SERVER_ERROR.value(), "OAUTH_ERROR_001", "구글에 요청한 Access 토큰에 대하여 응답 Body가 비어 있습니다."),
    OAUTH_USERINFO_REQUEST_FAILED_BY_NON_2XX_STATUS(HttpStatus.INTERNAL_SERVER_ERROR.value(), "OAUTH_ERROR_001", "구글에 전송한 회원정보 요청이 실패했습니다."),
    OAUTH_USERINFO_REQUEST_FAILED_BY_NON_EXIST_BODY(HttpStatus.INTERNAL_SERVER_ERROR.value(), "OAUTH_ERROR_001", "구글에 요청한 회원정보에 대하여 응답 Body가 비어 있습니다."),

    SIGNUP_INVALID_ID(HttpStatus.BAD_REQUEST.value(), "SIGNUP_ERROR_001", "잘못된 형식의 아이디입니다."),
    SIGNUP_INVALID_PASSWORD(HttpStatus.BAD_REQUEST.value(), "SIGNUP_ERROR_002", "잘못된 형식의 비밀번호입니다."),
    SIGNUP_ALREADY_REGISTER(HttpStatus.BAD_REQUEST.value(), "SIGNUP_ERROR_003", "이미 가입된 아이디입니다."),

    LOGIN_INVALID_ID_AND_PASSWORD(HttpStatus.BAD_REQUEST.value(), "LOGIN_ERROR_001", "아이디나 비밀번호가 다릅니다."),

    ;

    private final String message;

    AuthExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
