CREATE TABLE CustomerAccessLog(
    LOGID VARCHAR(36) PRIMARY KEY,
    CUSTOMERID VARCHAR(36) NOT NULL,
    USERNAME VARCHAR(20),
    LOGINTIME TIMESTAMP,
    LOGOUTTIME TIMESTAMP,
    FOREIGN KEY (CUSTOMERID) REFERENCES Customer(CUSTOMERID)
);
