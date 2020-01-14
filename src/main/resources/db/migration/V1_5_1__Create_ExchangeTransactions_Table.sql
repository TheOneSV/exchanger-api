CREATE TABLE IF NOT EXISTS exchange_transactions (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    account_id bigint NOT NULL,
    transaction_date_time timestamp NOT NULL,
    source_currency varchar(3) NOT NULL,
    target_currency varchar(3) NOT NULL,
    exchange_rate decimal(20, 4) NOT NULL,
    source_amount decimal(20, 4) NOT NULL,
    target_amount decimal(20, 4) NOT NULL,
    CONSTRAINT FK_ExchangeTransactions_Accounts FOREIGN KEY (account_id)
        REFERENCES accounts(id)
)