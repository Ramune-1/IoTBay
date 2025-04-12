package model;
public class StaffAccount {
    private String userName;
    private int staffID;
    private String gender;
    private String email;
    private String password;
    private String phone;

   
    public StaffAccount(String userName, int staffID, String gender, String email, String password, String phone) {
        this.userName = userName;
        this.staffID = staffID;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}