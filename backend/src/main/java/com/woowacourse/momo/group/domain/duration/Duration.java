package com.woowacourse.momo.group.domain.duration;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.woowacourse.momo.globalException.exception.ErrorCode;
import com.woowacourse.momo.globalException.exception.MomoException;
import com.woowacourse.momo.group.exception.InvalidDurationException;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Duration {

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    public Duration(LocalDate startDate, LocalDate endDate) {
        validatePastDate(startDate, endDate);
        validateEndIsNotBeforeStart(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validatePastDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())) {
            throw new MomoException(ErrorCode.GROUP_DURATION_NOT_PAST);
        }
    }

    private void validateEndIsNotBeforeStart(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new MomoException(ErrorCode.GROUP_DURATION_START_AFTER_END);
        }
    }

    public boolean isAfterStartDate(LocalDateTime localDateTime) {
        return startDate.isBefore(LocalDate.from(localDateTime));
    }
}
