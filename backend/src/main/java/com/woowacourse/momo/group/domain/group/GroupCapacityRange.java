package com.woowacourse.momo.group.domain.group;

import com.woowacourse.momo.global.exception.exception.ErrorCode;
import com.woowacourse.momo.global.exception.exception.MomoException;

public enum GroupCapacityRange {

    MINIMUM(1),
    MAXIMUM(99),
    ;

    private final int value;

    GroupCapacityRange(int value) {
        this.value = value;
    }

    public static void validateCapacityIsInRange(int capacity) {
        if (isOutOfRange(capacity)) {
            throw new MomoException(ErrorCode.GROUP_MEMBERS_NOT_IN_RANGE);
        }
    }

    private static boolean isOutOfRange(int capacity) {
        return (MINIMUM.value > capacity) || (capacity > MAXIMUM.value);
    }
}
