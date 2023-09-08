package com.example.aswemake_test.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mart_id")
    private Mart mart;

    @Column(unique = true, nullable = false, length = 20)
    private String name;
    private int price;
    private int stock;
    private boolean isDeleted;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<PriceHistory> priceHistoryList = new ArrayList<>();

    @Builder
    public Item(Mart mart, String name, int price, int stock, boolean isDeleted) {
        this.mart = mart;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.isDeleted = isDeleted;
    }

    // 수정 메서드
    public void modify(String name, int price){
        this.name = name;
        this.price = price;
    }

    // 삭제 메서드
    public void delete(){
        this.isDeleted = true;
    }
}
