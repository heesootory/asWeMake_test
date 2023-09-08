insert into users values
(1, "wldn8989@gmail.com", "지우", "1212"),
(2, "gusrhkd123@naver.com", "현광", "2323");

insert into mart values
(1, "우리마트", "1111"), (2, "국민마트", "3333");

insert into item values
(1, false, "양파", 3000,10, 1), (2, false, "고구마", 4500,20, 2), (3, false, "감자", 8000,15, 1), (4, false, "콩나물", 20000,12, 1), (5, false, "전복", 30000,8, 2),
(6, false, "파", 16000, 31, 2), (7, false, "명태", 7000,22, 1), (8, false, "연근", 12000,19, 2);

insert into price_history values
(1, 2000, '2019-08-18T11:44:30.327959', 1),
(2, 5000, '2021-11-08T11:44:30.327959', 1),
(3, 8000, '2022-02-03T11:44:30.327959', 1);

insert into coupon values
(1, "특정 2000원 할인 쿠폰" , 2000, NULL, false, "1111", "PERCENTAGE"),
(2, "특정 품목 10% 할인 쿠폰", NULL, 10, false, "2222", "FIXED"),
(3, "전종목 20% 할인 쿠폰", NULL, 20, true, "3333", "WHOLE_PERCENTAGE"),
(4, "전종목 3000 할인 쿠폰", 3000, NULL, true, "444", "WHOLE_FIXED"),
(5, "특정 30% 할인 쿠폰", NULL, 30, false, "5555", "PERCENTAGE"),
(6, "전종목 1000 할인 쿠폰", 1000, NULL, false, "6666", "FIXED");

insert into coupon_item values
(1, 2, 1), (2, 1, 2), (3, 6, 8), (4, 5, 7), (5, 1, 5), (6, 6, 3), (7, 6, 4), (8, 6, 6), (9, 6, 8);

insert into user_coupon values
(1, 1, 1, 1), (2, 2, 2, 2), (3, 1, 6, 1), (4, 1, 5, 2), (5, 1, 3, 1), (6, 1, 4, 2);

insert into orders values
(1, '2022-08-08T14:22:30.327959', 1),(2, '2021-11-08T11:44:30.327959', 2), (3, '2023-03-11T12:20:20.327959', 1);

insert into order_item values
(1, 2, 3, 1), (2, 3, 1, 1), (3, 1, 8, 1), (4, 1, 7, 1), (5, 1, 4, 2), (6, 2, 5, 2), (7, 1, 3, 2);