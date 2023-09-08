package com.example.aswemake_test.respository;

import com.example.aswemake_test.domain.Item;
import com.example.aswemake_test.domain.Mart;
import com.example.aswemake_test.domain.PriceHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Persistence Layer Test
 */

//@SpringBootTest
@DataJpaTest
class PriceHistoryJpaRepositoryTest {
    @Autowired
    private PriceHistoryJpaRepository priceHistoryJpaRepository;
    @Autowired
    private MartJpaRepository martJpaRepository;
    @Autowired
    private ItemJpaRepository itemJpaRepository;

    @DisplayName("원하는 날짜와 시간을 입력하면, 해당 시점 이전의 시간의 가격이 모두 조회된다.")
    @Test
    void findPriceLessThanUpdateDateTime() throws Exception{
        // given

        Mart mart1 = Mart.builder()
                .name("우리마트")
                .password("1111")
                .build();

        martJpaRepository.save(mart1);

        Item item1 = Item.builder()
                .mart(mart1)
                .name("수박")
                .stock(5)
                .price(10000)
                .isDeleted(false)
                .build();

        Item item2 = Item.builder()
                .mart(mart1)
                .name("메론")
                .stock(8)
                .price(7000)
                .isDeleted(false)
                .build();

        itemJpaRepository.saveAll(List.of(item1, item2));

        String strDate1 = "2020-10-26 14:28:00";
        String strDate2 = "2021-04-14 14:28:00";
        String strDate3 = "2022-09-08 14:28:00";
        LocalDateTime localDateTime1 = LocalDateTime.parse(strDate1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime localDateTime2 = LocalDateTime.parse(strDate2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime localDateTime3 = LocalDateTime.parse(strDate3, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        PriceHistory priceHistory1 = PriceHistory.builder()
                .item(item1)
                .price(15000)
                .updateDateTime(localDateTime1)
                .build();

        PriceHistory priceHistory2 = PriceHistory.builder()
                .item(item1)
                .price(8000)
                .updateDateTime(localDateTime2)
                .build();

        PriceHistory priceHistory3 = PriceHistory.builder()
                .item(item1)
                .price(11000)
                .updateDateTime(localDateTime3)
                .build();

        priceHistoryJpaRepository.saveAll(List.of(priceHistory1,priceHistory2,priceHistory3));

        // when
        String strDate = "2021-09-08 14:28:00";
        LocalDateTime localDateTime = LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<PriceHistory> priceHistoryList = priceHistoryJpaRepository.findPriceLessThanUpdateDateTime(localDateTime,item1);

        // then
        assertThat(priceHistoryList).hasSize(2)
                .extracting("price")
                .containsExactlyInAnyOrder(
                        8000, 15000
                );
    }




}