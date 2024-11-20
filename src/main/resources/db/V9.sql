ALTER TABLE reviewphotos DROP FOREIGN KEY reviewphotos_ibfk_1;

ALTER TABLE reviewphotos
ADD CONSTRAINT reviewphotos_ibfk_1
FOREIGN KEY (review_no) REFERENCES review (review_no)
ON DELETE CASCADE;