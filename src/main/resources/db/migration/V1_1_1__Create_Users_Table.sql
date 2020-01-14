CREATE TABLE IF NOT EXISTS users (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(40) NOT NULL,
	username varchar(20) NOT NULL,
	password char(60) NOT NULL
)