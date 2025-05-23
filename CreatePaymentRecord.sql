CREATE TABLE paymentrecord(
    paymentID INT ,
    ORDERID VARCHAR(36),
    paymentmethodID INT,
    
    PRIMARY KEY (paymentID),
    FOREIGN KEY (ORDERID) REFERENCES CustomerOrder(ORDERID),
    FOREIGN KEY (paymentmethodID) REFERENCES paymentmethod(paymentmethodID)





);