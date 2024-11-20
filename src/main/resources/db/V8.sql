CREATE TABLE reviewphotos (
      id INT PRIMARY KEY AUTO_INCREMENT,
      review_no INT,
      photo_url VARCHAR(255),
      FOREIGN KEY (review_no) REFERENCES review(review_no)
);