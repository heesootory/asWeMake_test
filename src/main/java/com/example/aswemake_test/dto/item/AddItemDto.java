package com.example.aswemake_test.dto.item;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddItemDto {

    private Long martId;
    private String name;
    private int stock;
    private int price;
    private boolean isDeleted;

    @Builder
    public AddItemDto(Long martId, String name, int stock, int price, boolean isDeleted) {
        this.martId = martId;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.isDeleted = isDeleted;
    }
}
