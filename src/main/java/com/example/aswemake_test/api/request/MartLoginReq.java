package com.example.aswemake_test.api.request;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MartLoginReq {

    private String name;
    private String password;

}
