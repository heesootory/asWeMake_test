package com.example.aswemake_test.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private Long uid;
    private String username;
    private String email;

    @Builder
    public UserDto(Long uid, String username, String email) {
        this.uid = uid;
        this.username = username;
        this.email = email;
    }
}
