CREATE TABLE paymentrecord(
    paymentrecordID INT ,
    ORDERID VARCHAR(36),
    paymentmethodID INT,
    paymentstatus VARCHAR(36),
    PRIMARY KEY (paymentrecordID),
    FOREIGN KEY (ORDERID) REFERENCES CustomerOrder(ORDERID),
    FOREIGN KEY (paymentmethodID) REFERENCES paymentmethod(paymentmethodID)





);