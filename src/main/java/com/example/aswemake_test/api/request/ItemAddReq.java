package com.example.aswemake_test.api.request;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemAddReq {

    private Long martId;
    private String name;
    private int price;

    @Builder
    public ItemAddReq(Long martId, String name, int price) {
        this.martId = martId;
        this.name = name;
        this.price = price;
    }
}
