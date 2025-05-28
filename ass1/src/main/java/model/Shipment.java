package model;

public class Shipment {
    private int shipmentId;
    private String orderId;
    private String customerId;
    private String address;
    private String shipmentDate;
    private String method;
    private String status;

    // Constructor
    public Shipment(int shipmentId, String orderId, String customerId, String address, String shipmentDate, String method, String status) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.address = address;
        this.shipmentDate = shipmentDate;
        this.method = method;
        this.status = status;
    }

    // Getters and Setters for all fields...
    public int getId() {
    return shipmentId;
}

public void setId(int shipmentId) {
    this.shipmentId = shipmentId;
}

public String getOrderId() {
    return orderId;
}

public void setOrderId(String orderId) {
    this.orderId = orderId;
}

public String getCustomerId() {
    return customerId;
}

public void setCustomerId(String customerId) {
    this.customerId = customerId;
}

public String getAddress() {
    return address;
}

public void setAddress(String address) {
    this.address = address;
}

public String getDate() {
    return shipmentDate;
}

public void setDate(String shipmentDate) {
    this.shipmentDate = shipmentDate;
}

public String getMethod() {
    return method;
}

public void setMethod(String method) {
    this.method = method;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

}