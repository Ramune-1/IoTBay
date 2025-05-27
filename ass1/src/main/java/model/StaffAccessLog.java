
package model;

import java.sql.Timestamp;



public class StaffAccessLog {
    private String logID;
    private String staffID;
    private String username;
    private Timestamp loginTime;
    private Timestamp logoutTime;

    public StaffAccessLog(String logID, String staffID, String username, Timestamp loginTime, Timestamp logoutTime) {
        this.logID = logID;
        this.staffID = staffID;
        this.username = username;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
    }

    public String getLogID() {
        return this.logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public String getStaffID() {
        return this.staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
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
