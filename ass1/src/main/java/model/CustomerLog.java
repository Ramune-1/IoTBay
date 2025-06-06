package model;

import java.sql.Timestamp;

public class CustomerLog {
    private String LogID;
    private String customerID;
    private String username;
    private Timestamp loginTime;
    private Timestamp logoutTime;


    public CustomerLog(String logID, String customerID, String username, Timestamp loginTime, Timestamp logoutTime) {
        this.LogID = logID;
        this.customerID = customerID;
        this.username = username;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
    }

    public String getLogID() {
        return this.LogID;
    }

    public void setLogID(String logID) {
        this.LogID = logID;
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getLogoutTime() {
        return this.logoutTime;
    }

    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }
}
