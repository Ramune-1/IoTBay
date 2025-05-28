CREATE TABLE Shipment (
    shipmentId TEXT PRIMARY KEY,                    
    ORDERID VARCHAR(36) NOT NULL,                   
    CUSTOMERID VARCHAR(36) NOT NULL,                
    address TEXT NOT NULL,
    shipmentDate TEXT NOT NULL,
    method TEXT NOT NULL,
    status TEXT NOT NULL,
    FOREIGN KEY (ORDERID) REFERENCES CustomerOrder(ORDERID),
    FOREIGN KEY (CUSTOMERID) REFERENCES Customer(CUSTOMERID)
);
