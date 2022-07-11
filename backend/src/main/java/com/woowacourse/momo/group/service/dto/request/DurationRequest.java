package com.woowacourse.momo.group.service.dto.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.woowacourse.momo.group.domain.duration.Duration;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DurationRequest {

    @DateTimeFormat
    private LocalDate start;
    @DateTimeFormat
    private LocalDate end;

    public Duration toEntity() {
        return new Duration(start, end);
    }
}
