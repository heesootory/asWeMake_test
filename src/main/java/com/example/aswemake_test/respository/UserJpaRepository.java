package com.example.aswemake_test.respository;

import com.example.aswemake_test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

}
