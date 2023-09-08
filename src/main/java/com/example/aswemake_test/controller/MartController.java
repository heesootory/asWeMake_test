package com.example.aswemake_test.controller;


import com.example.aswemake_test.api.request.ItemAddReq;
import com.example.aswemake_test.api.request.ItemUpdateReq;
import com.example.aswemake_test.api.request.MartLoginReq;
import com.example.aswemake_test.dto.item.AddItemDto;
import com.example.aswemake_test.dto.item.UpdateItemDto;
import com.example.aswemake_test.handler.ex.CustomApiException;
import com.example.aswemake_test.service.MartService;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Date;

@RestController
@RequestMapping("/api/mart/")
@AllArgsConstructor
public class MartController {

    private final MartService martService;

    @GetMapping("/health")
    public String status(){
        return "마트 직원 전용입니다.";
    }

    @PostMapping("/login")
    public String login(@RequestBody MartLoginReq martLoginReq){

        String password = martService.findPassword(martLoginReq.getName());
        if(!password.equals(martLoginReq.getPassword())){
            throw new CustomApiException("비밀번호가 일치하지 않습니다.");
        }

        // jwt 토큰 만들기
        Date now = new Date();
        String token = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("ADMIN")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis()))
                .claim("Authorization", "mart")
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();

        return token;
    }

    /**
     * 상품 생성
     * @return : 상품 id
     */
    @PostMapping("/regist")
    public ResponseEntity<?> itemAdd(@RequestBody ItemAddReq itemAddReq){
        AddItemDto addItemDto = AddItemDto.builder()
                .martId(itemAddReq.getMartId())
                .name(itemAddReq.getName())
                .price(itemAddReq.getPrice())
                .isDeleted(false)
                .build();
        Long itemId = martService.addItem(addItemDto);
        return new ResponseEntity<>(itemId, HttpStatus.CREATED);
    }

    /**
     * 상품 수정
     * @return : 수정된 상품 id
     */
    @PutMapping("/update")
    public ResponseEntity<?> itemUpdate(@RequestBody ItemUpdateReq itemUpdateReq){

        UpdateItemDto updateItemDto = UpdateItemDto.builder()
                .itemId(itemUpdateReq.getItemId())
                .name(itemUpdateReq.getName())
                .price(itemUpdateReq.getPrice())
                .build();
        // 상품 수정
        martService.updateItem(updateItemDto);

        return new ResponseEntity<>("수정완료", HttpStatus.OK);
    }

    /**
     * 상품 삭제
     * @param itemId
     * @return
     */
    @DeleteMapping ("/delete/{itemId}")
    public ResponseEntity<?> itemDelete(@PathVariable("itemId") Long itemId){
        martService.deleteItem(itemId);
        return new ResponseEntity<>("삭제완료", HttpStatus.OK);
    }



}
