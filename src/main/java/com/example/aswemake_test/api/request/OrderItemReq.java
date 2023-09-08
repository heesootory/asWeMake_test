package com.example.aswemake_test.api.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemReq {
    private Long orderId;
    private Long itemId;
    private int count;

}
