-- 여행지 리뷰 테이블
CREATE TABLE Review (
    review_no INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(50),  -- 사용자 id
    spot_id INT,  -- 여행지 id
    content TEXT,  -- 리뷰 내용
    score DECIMAL(2,1),  -- 평점
    write_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 리뷰작성일
    trip_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 여행일
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (spot_id) REFERENCES Spot(spot_id)
);

-- 여행지 평점 테이블(리뷰 작성시 업데이트)
CREATE TABLE SpotAvgScore (
    spot_id INT PRIMARY KEY,
    avg_score DECIMAL(3,2),
    review_count INT,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (spot_id) REFERENCES Spot(spot_id)
);

DELIMITER //

CREATE TRIGGER update_avg_score_insert AFTER INSERT ON Review
    FOR EACH ROW
BEGIN
    INSERT INTO SpotAvgScore (spot_id, avg_score, review_count)
    VALUES (NEW.spot_id, NEW.score, 1)
        ON DUPLICATE KEY UPDATE
                             avg_score = (avg_score * review_count + NEW.score) / (review_count + 1),
                             review_count = review_count + 1;
END//

CREATE TRIGGER update_avg_score_update AFTER UPDATE ON Review
    FOR EACH ROW
BEGIN
    UPDATE SpotAvgScore
    SET avg_score = (avg_score * review_count - OLD.score + NEW.score) / review_count
    WHERE spot_id = NEW.spot_id;
END//

CREATE TRIGGER update_avg_score_delete AFTER DELETE ON Review
    FOR EACH ROW
BEGIN
    UPDATE SpotAvgScore
    SET avg_score = IF(review_count > 1, (avg_score * review_count - OLD.score) / (review_count - 1), 0),
        review_count = review_count - 1
    WHERE spot_id = OLD.spot_id;

    DELETE FROM SpotAvgScore
    WHERE spot_id = OLD.spot_id AND review_count = 0;
END//

DELIMITER ;