package com.example.aswemake_test.dto.item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemDto {

    private Long orderId;
    private Long itemId;
    private int count;

    @Builder
    public OrderItemDto(Long orderId, Long itemId, int count) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.count = count;
    }
}
