package com.example.aswemake_test.respository;

import com.example.aswemake_test.domain.Item;
import com.example.aswemake_test.domain.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceHistoryJpaRepository extends JpaRepository<PriceHistory, Long> {

    @Query("select p from PriceHistory p " +
            "where p.updateDateTime < :updateDateTime and p.item = :item " +
            "order by p.updateDateTime asc")
    List<PriceHistory> findPriceLessThanUpdateDateTime(@Param("updateDateTime")LocalDateTime updateDateTime, @Param("item")Item item);
}
