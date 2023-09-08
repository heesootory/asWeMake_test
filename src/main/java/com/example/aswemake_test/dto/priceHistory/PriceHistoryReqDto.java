package com.example.aswemake_test.dto.priceHistory;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PriceHistoryReqDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTime;

    private Long itemId;

    @Builder
    public PriceHistoryReqDto(LocalDateTime dateTime, Long itemId) {
        this.dateTime = dateTime;
        this.itemId = itemId;
    }
}
