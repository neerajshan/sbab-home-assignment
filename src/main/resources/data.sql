DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    username            VARCHAR(250) NOT NULL,
    super_secret_secret VARCHAR(32)  NOT NULL
);

INSERT INTO users (username, super_secret_secret)
VALUES ('neeraj', 'KBWUUPIJZ4ZOPUMT3OJHHI4JTWPWUKP5');