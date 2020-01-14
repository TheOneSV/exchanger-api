CREATE TABLE IF NOT EXISTS accounts (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id bigint NOT NULL,
    CONSTRAINT FK_Accounts_Users FOREIGN KEY (user_id)
        REFERENCES users(id)
)