-- 닉네임 칼럼 추가
ALTER TABLE Profile
    ADD COLUMN nickname VARCHAR(100);

-- 사진 칼럼 추가
ALTER TABLE Profile
    ADD COLUMN photo VARCHAR(255);