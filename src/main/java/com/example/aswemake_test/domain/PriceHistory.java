package com.example.aswemake_test.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_history_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    private int price;
    private LocalDateTime updateDateTime;

    @Builder
    public PriceHistory(Item item, int price, LocalDateTime updateDateTime) {
        this.item = item;
        this.price = price;
        this.updateDateTime = updateDateTime;
    }
}
