package com.example.aswemake_test.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    private String serialNumber;

    @Column(unique = true, nullable = false, length = 300)
    private String name;

    @Enumerated(EnumType.STRING)
    private CouponType type;

    @Column(nullable = true)
    private int salePercentage;
    @Column(nullable = true)
    private int saleFixedPrice;
    private boolean saleWhole;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    private List<CouponItem> couponItems = new ArrayList<>();

}
