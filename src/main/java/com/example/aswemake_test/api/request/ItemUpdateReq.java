package com.example.aswemake_test.api.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemUpdateReq {

    private Long itemId;
    private String name;
    private int price;

    @Builder
    public ItemUpdateReq(Long itemId, String name, int price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }
}
