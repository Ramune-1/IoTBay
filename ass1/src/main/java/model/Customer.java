package model;

public  class Customer {
    private String customerID;
    private String userName;
    private String name;
    private String gender;
    private String gmail;
    private String passWord;
    private String phone;

    public Customer(String customerID, String userName, String name, String gmail, String passWord, String phone, String gender) {
        this.customerID = customerID;
        this.userName = userName;
        this.name = name;
        this.gender = gender;
        this.gmail = gmail;
        this.passWord = passWord;
        this.phone = phone;
    }



    public String getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}    