package com.woowacourse.momo.participant.exception;

import com.woowacourse.momo.global.exception.exception.ExceptionMessage;

public enum ParticipantExceptionMessage implements ExceptionMessage {

    HOST_CANNOT_PARTICIPATE_OWN_GROUP("주최자는 자신의 모임에 참여할 수 없습니다."),
    PARTICIPANT_CANNOT_PARTICIPATE_IN_ALREADY_PARTICIPATED_GROUP("참여자는 본인이 참여한 모임에 재참여할 수 없습니다."),
    MEMBER_CANNOT_PARTICIPATE_IN_CLOSED_GROUP("마감된 모임에는 참여할 수 없습니다."),
    HOST_CANNOT_LEAVE_OWN_GROUP("주최자는 모임에 탈퇴할 수 없습니다."),
    MEMBER_IS_NOT_PARTICIPATED_IN("모임의 참여자가 아닙니다."),
    PARTICIPANT_LEAVE_DEADLINE("모집이 마감된 모임입니다."),
    GROUP_ALREADY_CLOSED("조기종료된 모임입니다."),
    ;

    private final String message;

    ParticipantExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
