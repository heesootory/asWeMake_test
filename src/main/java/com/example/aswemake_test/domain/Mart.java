package com.example.aswemake_test.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Mart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mart_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    private String password;

    @OneToMany(mappedBy = "mart", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @Builder
    public Mart(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
