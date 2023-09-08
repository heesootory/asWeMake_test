package com.example.aswemake_test.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;

public class WebInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 요청 들어가기 전
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("================ URL 요청 전 인터셉터 ==================");

        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        jwtToken = jwtToken.replaceAll("Bearer", "");
        System.out.println(jwtToken);
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey("secret")
                .parseClaimsJws(jwtToken);

        String authorization = claims.getBody().get("Authorization", String.class);
        if(!authorization.equals("mart")) {
            request.getRequestDispatcher("/api/error/authorization").forward(request, response);
            return false;
        }
        return true;
    }


}
