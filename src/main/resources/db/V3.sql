CREATE TABLE refreshtoken (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   refresh_token VARCHAR(255) NOT NULL,
   key_user_id VARCHAR(255) NOT NULL
);