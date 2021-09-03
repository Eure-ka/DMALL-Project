use dmall;

CREATE TABLE `admin` (
  `admin_id` varchar(45) NOT NULL,
  `admin_pw` varchar(45) DEFAULT NULL,
  `admin_position` varchar(45) DEFAULT NULL,
  `admin_reg_date` date DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='관리자 테이블';

CREATE TABLE `category` (
  `category_id` int NOT NULL,
  `category_name` varchar(45) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품정보별 카테고리 테이블';


CREATE TABLE `product` (
  `product_code` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `product_price` int NOT NULL,
  `category_id` int DEFAULT NULL,
  `img_path` varchar(1000) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `product_amount` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `product_reg_date` date DEFAULT '2021-01-01',
  `product_shipping_fee` int DEFAULT '2500',
  `admin_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `sumimg_path` varchar(1000) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`product_code`),
  KEY `fk_product_category_idx` (`category_id`),
  KEY `fk_product_admin_idx` (`admin_id`),
  CONSTRAINT `fk_product_admin` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci COMMENT='상품 정보 테이블';


CREATE TABLE `coupon` (
  `coupon_id` int NOT NULL AUTO_INCREMENT,
  `coupon_name` varchar(45) NOT NULL,
  `coupon_amount` int NOT NULL,
  `coupon_rate` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`coupon_id`),
  KEY `fk_category_id_idx` (`category_id`),
  CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `product` (`category_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='쿠폰 테이블';

CREATE TABLE `purchase` (
  `purchase_code` int NOT NULL,
  `product_id` varchar(45) NOT NULL,
  `user_id` varchar(45) NOT NULL,
  `user_address` varchar(45) NOT NULL,
  `payment_method` varchar(45) NOT NULL,
  PRIMARY KEY (`purchase_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='구매 테이블';

CREATE TABLE `review` (
  `product_code` int DEFAULT NULL,
  `rnum` int NOT NULL AUTO_INCREMENT,
  `review_content` varchar(45) NOT NULL,
  `user_id` varchar(40) DEFAULT NULL,
  `review_date` date DEFAULT NULL,
  `review_img` varchar(100) DEFAULT NULL,
  `helpful` int DEFAULT '0',
  PRIMARY KEY (`rnum`),
  KEY `fk_review_product_idx` (`product_code`),
  CONSTRAINT `fk_review_product` FOREIGN KEY (`product_code`) REFERENCES `product` (`product_code`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='리뷰 테이블';


CREATE TABLE `user` (
  `user_id` varchar(40) NOT NULL,
  `user_pw` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `user_phone` varchar(45) DEFAULT NULL,
  `user_email` varchar(45) DEFAULT NULL,
  `user_reg_date` date DEFAULT '2021-01-01',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='유저 테이블';

CREATE TABLE `shpping_bk` (
  `bk_num` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) DEFAULT NULL,
  `product_code` int DEFAULT NULL,
  `bk_resultnumber` int DEFAULT NULL,
  PRIMARY KEY (`bk_num`),
  KEY `fk_shopping_bk_user_idx` (`user_id`),
  KEY `fk_shopping_bk_product_idx` (`product_code`),
  CONSTRAINT `fk_shopping_bk_product` FOREIGN KEY (`product_code`) REFERENCES `product` (`product_code`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_shopping_bk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='장바구니 테이블';

select * from user;

insert into user values ('1','1','1','1','1',now());

select * from admin;

insert into admin values ('admin','1234','gg',now());

select * from category;

insert into category values('100','스포츠/레저');
insert into category values('200','문구');
insert into category values('300','식품');
insert into category values('400','뷰티');

select * from product;


insert into product values ('66', '덮밥', '200000', '300', '87cdef523aec4142a60c407d7339342dbab.jpg', '222222', '2021-08-26', '2500', 'admin', 'thumnail_87cdef523aec4142a60c407d7339342dbab.jpg'
);
insert into product values ('82', '잡곡', '19800', '300', 'a82dad9c76e34a3a80d8dd2056fa966e잡곡.PNG', '62', '2021-08-30', '2500', 'admin', 'thumnail_a82dad9c76e34a3a80d8dd2056fa966e잡곡.PNG');
insert into product values ('81', '단소', '4700', '200', '74d87f951e374eac960cfa5ddbaeb366단소.PNG', '33', '2021-08-30', '0', 'admin', 'thumnail_74d87f951e374eac960cfa5ddbaeb366단소.PNG'
);
insert into product values ('80', '각도기', '2300', '200', '4fa7e7dd8cd04443bdcc43216ea804b9각도기.PNG', '87', '2021-08-30', '0', 'admin', 'thumnail_4fa7e7dd8cd04443bdcc43216ea804b9각도기.PNG'
);
insert into product values ('79', '계란', '6800', '300', 'e5814a01956a47f18872ff12de0d97e2계란.PNG', '50', '2021-08-30', '0', 'admin', 'thumnail_e5814a01956a47f18872ff12de0d97e2계란.PNG'
);
insert into product values ('78', '아이라이너', '9900', '400', '589a61972ed24ce7bff293e7f3d1e65d아이라이너.PNG', '86', '2021-08-30', '0', 'admin', 'thumnail_589a61972ed24ce7bff293e7f3d1e65d아이라이너.PNG'
);
insert into product values ('77', '가위', '1300', '200', '7734d9750c6f4e41a65d773358b9835a가위.PNG', '200', '2021-08-30', '0', 'admin', 'thumnail_7734d9750c6f4e41a65d773358b9835a가위.PNG'
);
insert into product values ('76', '축구공', '15200', '100', '6d182d11eb294e2496770dc5c5c7cb12축구공.PNG', '63', '2021-08-30', '0', 'admin', 'thumnail_6d182d11eb294e2496770dc5c5c7cb12축구공.PNG'
);
insert into product values ('75', '간고등어', '3700', '300', '7d60f13fe68949119e182c2bc0b5f466간고등어.PNG', '58', '2021-08-30', '2500', 'admin', 'thumnail_7d60f13fe68949119e182c2bc0b5f466간고등어.PNG'
);
insert into product values ('74', '에센스', '32000', '400', 'fb0d40d031df465f857d7feaa15fb3eb화장품1.PNG', '22', '2021-08-30', '2500', 'admin', 'thumnail_fb0d40d031df465f857d7feaa15fb3eb화장품1.PNG'
);
insert into product values ('73', '냉동삼겹살', '4500', '300', '7017b317ec6249c88ed88c3f009b3d51냉동삼겹살.PNG', '99', '2021-08-30', '2500', 'admin', 'thumnail_7017b317ec6249c88ed88c3f009b3d51냉동삼겹살.PNG'
);

