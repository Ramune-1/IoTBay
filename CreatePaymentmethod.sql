CREATE TABLE paymentmethod (
    --required feilds 
    paymentmethodID INT,
    paymentoption VARCHAR (36) NOT NULL,
    ownername VARCHAR (100) NOT NULL,
    -- credit card specifc data types 
    cardnum  VARCHAR (36) ,
    cvv INT (10) ,
    expirydate DATE ,
    -- digital options like paypal or apple 
    accountname VARCHAR(100),
    -- constraints 
    PRIMARY KEY (paymentmethodID)
);