package com.example.aswemake_test.service;

import com.example.aswemake_test.domain.*;
import com.example.aswemake_test.dto.item.OrderItemDto;
import com.example.aswemake_test.dto.priceHistory.PriceHistoryReqDto;
import com.example.aswemake_test.dto.user.UserDto;
import com.example.aswemake_test.handler.ex.CustomApiException;
import com.example.aswemake_test.respository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final ItemJpaRepository itemJpaRepository;
    private final PriceHistoryJpaRepository priceHistoryJpaRepository;
    private final OrderJpaRepository orderJpaRepository;
    private final OrderItemJpaRepository orderItemJpaRepository;

    /**
     * 로그인
     */
    public String findPassword(String name){
        Optional<User> UserOptional = userJpaRepository.findByName(name);
        if(UserOptional.isEmpty()){
            throw new CustomApiException("해당 회원이 존재하지 않습니다.");
        }
        User user = UserOptional.get();
        return user.getPassword();
    }

    /**
     * 전체 회원 조회
     * @return : userDto 리스트
     */
    public List<UserDto> findAllUser(){
        List<User> userList = userJpaRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        userList.forEach( v ->
                userDtoList.add(new UserDto(v.getId(), v.getName(), v.getEmail()))
        );

        return userDtoList;
    }

    /**
     * 해당 시점의 상품 가격 조회
     * @param priceHistoryReqDto
     * @return
     */
    public int findTimeDatePrice(PriceHistoryReqDto priceHistoryReqDto){
        Optional<Item> OptionalItem = itemJpaRepository.findById(priceHistoryReqDto.getItemId());
        if(OptionalItem.isEmpty()){
            // 예외처리 - 찾는 아이템이 없을때
        }
        Item targetItem = OptionalItem.get();
        List<PriceHistory> priceList
                = priceHistoryJpaRepository.findPriceLessThanUpdateDateTime(priceHistoryReqDto.getDateTime(), targetItem);
        if(priceList.isEmpty()){
            // 예외처리 - 찾는 아이템 가격 기록이 없을때
        }
        int len = priceList.size();
        PriceHistory lastPriceHistory = priceList.get(len - 1);

        return lastPriceHistory.getPrice();
    }

    /**
     * 주문 생성
     * @param userId
     */
    public Long addOrder(Long userId){
        Optional<User> OptionalUser = userJpaRepository.findById(userId);
        if(OptionalUser.isEmpty()){
            // 예외처리 - 찾는 유저이 없을때
        }
        User user = OptionalUser.get();
        Order order = Order.builder()
                .user(user)
                .orderDateTime(LocalDateTime.now())
                .build();
        Long orderId = orderJpaRepository.save(order).getId();
        return orderId;
    }

    public String addOrderItem(OrderItemDto orderItemDto){
        Optional<Order> OptionalOrder = orderJpaRepository.findById(orderItemDto.getOrderId());
        if(OptionalOrder.isEmpty()){
            // 예외처리 - 찾는 주문이 없을때
        }
        Order order = OptionalOrder.get();

        Optional<Item> OptionalItem = itemJpaRepository.findById(orderItemDto.getItemId());
        if(OptionalItem.isEmpty()){
            // 예외처리 - 찾는 아이템이 없을때
        }
        Item item = OptionalItem.get();

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .item(item)
                .count(orderItemDto.getCount())
                .build();

        orderItemJpaRepository.save(orderItem);
        return item.getName();
    }

    public void getCount(Long userId){




    }

}
