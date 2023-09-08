package com.example.aswemake_test.controller;

import com.example.aswemake_test.api.request.OrderItemReq;
import com.example.aswemake_test.api.request.TimeDateReq;
import com.example.aswemake_test.api.request.UserLoginReq;
import com.example.aswemake_test.api.response.PriceFindByTimeRes;
import com.example.aswemake_test.api.response.UserListRes;
import com.example.aswemake_test.dto.item.OrderItemDto;
import com.example.aswemake_test.dto.priceHistory.PriceHistoryReqDto;
import com.example.aswemake_test.dto.user.UserDto;
import com.example.aswemake_test.handler.ex.CustomApiException;
import com.example.aswemake_test.service.UserService;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    // 상태체크
    @GetMapping("/health")
    public String status(){
        return "welcome";
    }


    @PostMapping("/login")
    public String login(@RequestBody UserLoginReq userLoginReq){
        String password = userService.findPassword(userLoginReq.getName());
        if(!password.equals(userLoginReq.getPassword())){
            throw new CustomApiException("비밀번호가 일치하지 않습니다.");
        }

        // jwt 토큰 만들기
        Date now = new Date();

        String token = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("ADMIN")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis()))
                .claim("Authorization", "user")
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();

        return token;
    }


    /**
     * 전체 유저 조회
     * @return
     */
    @GetMapping("/users")
    public ResponseEntity<?> usersList(){
        List<UserDto> userList = userService.findAllUser();
        return new ResponseEntity<>(new UserListRes(userList), HttpStatus.OK);
    }

    /**
     * 특정 시점 해당 상품의 가격 조회
     * @param timeDateReq
     * @return
     */
    @GetMapping("/priceAt")
    public ResponseEntity<?> priceFind(@RequestBody TimeDateReq timeDateReq){
        PriceHistoryReqDto priceHistoryReqDto = PriceHistoryReqDto.builder()
                .dateTime(timeDateReq.getDateTime())
                .itemId(timeDateReq.getItemId())
                .build();

        int price = userService.findTimeDatePrice(priceHistoryReqDto);
        PriceFindByTimeRes priceFindByTimeRes = new PriceFindByTimeRes(price);
        return new ResponseEntity<>(priceFindByTimeRes, HttpStatus.OK);
    }

    /**
     * 장바구니에 담기
     */
    @PostMapping("/order/{userId}")
    public ResponseEntity<?> Order(@PathVariable("userId") Long userId, @RequestBody OrderItemReq orderItemReq){
        //없는 주문번호라면 예외처리.

        // 주문아이템에 해당 주문id로 아이템과 갯수를 생성
        OrderItemDto orderItemDto = OrderItemDto.builder()
                .orderId(orderItemReq.getOrderId())
                .itemId(orderItemReq.getItemId())
                .count(orderItemReq.getCount())
                .build();
        String itemName = userService.addOrderItem(orderItemDto);
        return new ResponseEntity<>(itemName + "주문되었습니다.", HttpStatus.OK);
    }

    /**
     * 총 가격 구하기
     */
    @GetMapping("/totalPrice/{userId}")
    public ResponseEntity<?> totalPriceGet(@PathVariable("userId") Long orderId){
        // 주문했던 상품들 전부 찾기


        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
