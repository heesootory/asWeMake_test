package com.example.aswemake_test.dto.item;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateItemDto {

    private Long itemId;
    private String name;
    private int price;

    @Builder
    public UpdateItemDto(Long itemId, String name, int price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }
}
