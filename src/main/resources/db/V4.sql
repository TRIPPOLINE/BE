-- 여행 계획 테이블
CREATE TABLE Plan (
                      plan_id INT PRIMARY KEY,  -- 계획 id
                      user_id VARCHAR(50), -- 사용자 id
                      plan_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 계획 일자
                      trip_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 여행 일자
                      FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- 여행 장소 테이블
CREATE TABLE Place (
                       content_id INT,  -- 여행지 id
                       plan_id INT,  -- 계획 id
                       visit_order INT,  -- 방문 순서
                       PRIMARY KEY (content_id, plan_id),
                       FOREIGN KEY (plan_id) REFERENCES Plan(plan_id)
);