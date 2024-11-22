use trip;
ALTER TABLE plan
DROP FOREIGN KEY plan_ibfk_1;

ALTER TABLE plan
    ADD CONSTRAINT plan_ibfk_1 FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE;



ALTER TABLE review
DROP FOREIGN KEY review_ibfk_1;

ALTER TABLE review
    ADD CONSTRAINT review_ibfk_1
        FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE;