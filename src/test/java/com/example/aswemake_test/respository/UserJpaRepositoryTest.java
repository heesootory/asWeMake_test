package com.example.aswemake_test.respository;

import com.example.aswemake_test.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Persistence Layer Test
 */

@DataJpaTest
class UserJpaRepositoryTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @DisplayName("이름을 입력하면, 해당 회원이 조회된다.")
    @Test
    public void findByName() throws Exception{
        // given
        User user1 = User.builder()
                .name("창걸")
                .email("gusrhkd@naver.com")
                .password("1111")
                .build();

        User user2 = User.builder()
                .name("승호")
                .email("wldn@naver.com")
                .password("2222")
                .build();

        User user3 = User.builder()
                .name("희수")
                .email("gmltn@naver.com")
                .password("3333")
                .build();

        userJpaRepository.saveAll(List.of(user1, user2, user3));

        // when
        // 1. 존재하는 회원일때
        Optional<User> users = userJpaRepository.findByName("희수");

        // 2. 존재하지 않는 회원일때

        // then
        assertTrue(users.isPresent());
        users.ifPresent(user -> {
            assertEquals("희수", user.getName());
        });
    }


}