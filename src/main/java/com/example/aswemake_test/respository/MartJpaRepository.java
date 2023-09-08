package com.example.aswemake_test.respository;

import com.example.aswemake_test.domain.Mart;
import com.example.aswemake_test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface MartJpaRepository extends JpaRepository<Mart, Long> {

    Optional<Mart> findByName(String name);

}
