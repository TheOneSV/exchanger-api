CREATE TABLE IF NOT EXISTS users_roles (
	 id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
	CONSTRAINT FK_USER_ID FOREIGN KEY (user_id)
    REFERENCES users(id),
	CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id)
    REFERENCES roles(id)
)

