-- 리뷰의 제목 칼럼 추가--
ALTER TABLE review ADD COLUMN title VARCHAR(255) NULL AFTER spot_id;

-- <<리뷰 검색을 위한 fulltext 인덱스 생성>>
    -- [필수 설정]
    -- 1. mysql 설정 파일인 my.ini를 관리자 권한으로 연다
    -- 2. [mysqld]바로 밑줄에 innodb_ft_min_token_size=1을 추가한다
    -- 3. 워크 벤치 종료한 채로, WINDOW + R 하고 services.msc 입력
    -- 4. 목록을 뒤져서 mysql8.0을 누르고 재시작 누른다
    -- 5. 워크 벤치를 다시 켠 후 아래의 명령어를 수행한다
SHOW VARIABLES LIKE 'innodb_ft_min_token_size'; --my.ini에 설정한 토큰 크기를 확인 한다
ALTER TABLE spot ADD FULLTEXT INDEX idx_fulltext_spot (title, addr1) WITH PARSER ngram;
ALTER TABLE review ADD FULLTEXT INDEX idx_title (title);
ALTER TABLE review ADD FULLTEXT INDEX idx_content (content);
ALTER TABLE review ADD FULLTEXT INDEX idx_fulltext_review (title, content) WITH PARSER ngram;


CREATE TABLE `reviewlikes` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `review_no` int NOT NULL,
                               `user_id` varchar(255) NOT NULL,
                               `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `review_user_unique` (`review_no`,`user_id`),
                               KEY `review_likes_ibfk_1` (`review_no`),
                               KEY `review_likes_ibfk_2` (`user_id`),
                               CONSTRAINT `review_likes_ibfk_1` FOREIGN KEY (`review_no`) REFERENCES `review` (`review_no`) ON DELETE CASCADE,
                               CONSTRAINT `review_likes_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;