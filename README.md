#asWeMake_test

# Dummy data
    - src/resources/data.sql

# Environment
  * application-dev : 개발 환경(더미 데이터 적용)
  * application-test : 테스트 환경(더미 데이터 적용X)

# Version
 
* Springboot : 3.0.10
  - Spring Data JPA
  - Lombok
  - Spring Web
  - JUnit5

* Mysql 

# Description

* interceptor에서 jwt 토큰을 이용한 권한체크.
* persistence Layer test. 
* business Layer test 예정..

# ERD




---

## 서비스 요구사항

1. 마트 권한과 일반 사용자 권한이 구분되어있다.
2. 상품에 대한 생성, 수정, 삭제는 마트 권한이 필요하다.
3. 상품을 생성할 수 있다.
4. 상품 가격을 수정할 수 있다.
5. 특정 시점의 상품 가격을 조회할 수 있다.
  - 예시
    - 2023-01-01 00:00:00 시점의 A 상품 가격 = 1500원
    - 2023-01-15 12:00:00 시점의 A 상품 가격 = 2000원
6. 상품을 삭제할 수 있다.
7. 주문에 대한 총 금액을 계산할 수 있다.
  - (각 주문 목록의 상품가격 * 개수) + 배달비
8. 주문에 대한 필요 결제 금액을 계산할 수 있다.
  - 쿠폰을 적용하는 경우, 쿠폰으로 할인되는 금액을 반영해서 계산

## 필수 데이터

- 상품
  - 상품명
  - 가격
- 쿠폰
  - 쿠폰 적용 방법
    - 비율 / 고정
    - 각 적용 방법에 따른 적용 비율 / 적용 금액
  - 쿠폰 적용 범위
    - 주문 전체 (배달비 제외)
    - 특정 상품 한정 (특정 상품의 모든 개수에 적용)
- 주문
  - 주문 목록
    - 상품
    - 개수
  - 배달비
