package model;

public abstract class User {
    private String userName;
    private String name;
    private String gender;
    private String gmail;
    private String passWord;
    private int phone;

    public User(String userName, String name, String gender, String gmail, String passWord, int phone) {
        this.userName = userName;
        this.name = name;
        this.gender = gender;
        this.gmail = gmail;
        this.passWord = passWord;
        this.phone = phone;
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

    public int getPhone() {
        return this.phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

}