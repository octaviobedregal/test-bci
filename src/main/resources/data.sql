--DROP TABLE IF EXISTS users;

--CREATE TABLE users (
--  id INTEGER AUTO_INCREMENT  PRIMARY KEY,
--  name VARCHAR(50) NOT NULL,
--  email VARCHAR(50) NOT NULL,
--  password VARCHAR(64) NOT NULL
--);

INSERT INTO users (id, name, email, password, created, modified, last_Login, token, is_Active) VALUES
  (1,
  'octavio',
  'octavio@gmail.com',
  '$2a$10$NV16Wr/Fvo./yUu/yLH9He2aMvkCQQK3pz3LecLt6iKNFeT85xZMe'
  ,CURRENT_TIMESTAMP()
  ,CURRENT_TIMESTAMP()
  ,''
  ,'$2a$10$NV16Wr/Fvo./yUu/yLH9He2aMvkCQQK3pz3LecLt6iKNFeT85xZMe'
  ,true);
