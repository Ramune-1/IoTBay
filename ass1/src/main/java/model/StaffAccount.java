package model;

public class StaffAccount {
    private String staffID;
    private String userName;
    private String name;
    private String gender;
    private String gmail;
    private String password;
    private String phone;

    public StaffAccount(String staffID, String userName, String name, String gmail, String password, String phone, String gender) {
        this.staffID = staffID;
        this.userName = userName;
        this.name = name;
        this.gender = gender;
        this.gmail = gmail;
        this.password = password;
        this.phone = phone;
    }

    public String getStaffID() {
        return this.staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGmail() {
        return this.gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}