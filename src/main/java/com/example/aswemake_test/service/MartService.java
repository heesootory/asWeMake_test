package com.example.aswemake_test.service;

import com.example.aswemake_test.domain.Item;
import com.example.aswemake_test.domain.Mart;
import com.example.aswemake_test.domain.PriceHistory;
import com.example.aswemake_test.dto.item.AddItemDto;
import com.example.aswemake_test.dto.item.UpdateItemDto;
import com.example.aswemake_test.dto.priceHistory.PriceHistoryReqDto;
import com.example.aswemake_test.handler.ex.CustomApiException;
import com.example.aswemake_test.respository.ItemJpaRepository;
import com.example.aswemake_test.respository.MartJpaRepository;
import com.example.aswemake_test.respository.PriceHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MartService {

    private final MartJpaRepository martJpaRepository;
    private final ItemJpaRepository itemJpaRepository;
    private final PriceHistoryJpaRepository priceHistoryJpaRepository;

    /**
     * 로그인
     */
    public String findPassword(String name){
        Optional<Mart> MartOptional = martJpaRepository.findByName(name);
        if(MartOptional.isEmpty()){
            throw new CustomApiException("해당 마트가 존재하지 않습니다.");
        }
        Mart mart = MartOptional.get();
        return mart.getPassword();
    }

    /**
     * 상품 생성
     * @param addItemDto
     * @return : 생성된 상품 id
     */
    public Long addItem(AddItemDto addItemDto){
        Optional<Mart> MartOptional = martJpaRepository.findById(addItemDto.getMartId());
        if(MartOptional.isEmpty()){
            throw new CustomApiException("해당 마트가 존재하지 않습니다.");
        }
        Mart mart = MartOptional.get();

        // 해당 마트에 이미 존재하는 상품이라면 기존 상품 갯수에다가 추가.
        // 그게 아니라면, 새로 추가.

        Item item = Item.builder()
                .mart(mart)
                .name(addItemDto.getName())
                .stock(addItemDto.getStock())
                .price(addItemDto.getPrice())
                .isDeleted(addItemDto.isDeleted())
                .build();
        Long item_id = itemJpaRepository.save(item).getId();
        return item_id;
    }

    /**
     * 상품 조회
     */
    public Long findItemById(Long id){
         Optional<Item> OptionalItem = itemJpaRepository.findById(id);
         if(OptionalItem.isEmpty()){
             // 예외처리
         }
         Item item = OptionalItem.get();
         Long item_id = item.getId();
         return item_id;
    }

    /**
     * 상품 수정
     * @param updateItemDto
     * @return : 가격이 변경되었는지 유무.
     */
    public void updateItem(UpdateItemDto updateItemDto){
        Optional<Item> OptionalItem = itemJpaRepository.findById(updateItemDto.getItemId());
        if(OptionalItem.isEmpty()){
            // 예외처리 - 찾는 아이템이 없을때
        }
        Item originItem = OptionalItem.get();

        // 가격이 변경되는지 체크 - 가격이 변경된다면 true
        boolean priceChange = (updateItemDto.getPrice() != originItem.getPrice());

        // 변경사항 수정
        originItem.modify(updateItemDto.getName(), updateItemDto.getPrice());
        itemJpaRepository.save(originItem);

        // 가격이 변경되었다면, 히스토리에 내역 저장
        if(priceChange) addPriceHistory(originItem);
    }

    /**
     * 가격 변경 내역 저장
     */
    public void addPriceHistory(Item item){
        LocalDateTime dateTime = LocalDateTime.now();
        PriceHistory priceHistory = PriceHistory.builder()
                        .item(item)
                        .price(item.getPrice())
                        .updateDateTime(dateTime)
                        .build();

        priceHistoryJpaRepository.save(priceHistory);
    }

    /**
     * 상품 삭제
     * @param itemId
     */
    public void deleteItem(Long itemId){
        Optional<Item> OptionalItem = itemJpaRepository.findById(itemId);
        if(OptionalItem.isEmpty()){
            // 예외처리 - 찾는 아이템이 없을때
        }
        Item targetItem = OptionalItem.get();
        targetItem.delete();
        itemJpaRepository.save(targetItem);
    }














}
