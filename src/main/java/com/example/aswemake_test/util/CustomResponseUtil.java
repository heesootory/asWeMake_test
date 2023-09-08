package com.example.aswemake_test.util;

import com.example.aswemake_test.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomResponseUtil {
    private static final Logger log = LoggerFactory.getLogger(CustomResponseUtil.class);

    public static void unAuthentication(HttpServletResponse response, String msg){
        try{
            ObjectMapper om = new ObjectMapper();       // 객체를 json으로 바꿔줌는 역할
            ResponseDto<?> responseDto = new ResponseDto<>(-1, msg, null);
            String responseBody = om.writeValueAsString(responseDto);       // json으로 변경.
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(401);
            // 예쁘게 메시지를 포장하는 공통적인 응답 DTO를 만들어보자!
            response.getWriter().println(responseBody);
        }catch(Exception e){
            log.error("서버 파싱 에러");
        }
    }

    public static void unAuthorization(HttpServletResponse response, String msg){
        try{
            ObjectMapper om = new ObjectMapper();       // 객체를 json으로 바꿔줌는 역할
            ResponseDto<?> responseDto = new ResponseDto<>(-1, msg, null);
            String responseBody = om.writeValueAsString(responseDto);       // json으로 변경.
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(403);
            // 예쁘게 메시지를 포장하는 공통적인 응답 DTO를 만들어보자!
            response.getWriter().println(responseBody);
        }catch(Exception e){
            log.error("서버 파싱 에러");
        }
    }

}
