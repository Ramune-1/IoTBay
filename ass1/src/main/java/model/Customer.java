package model;
import java.util.Objects;

public class Customer extends User{

    public Customer(String userName, String name, String gender, String gmail, String passWord, int phone) {
        super(name, gender, gmail, passWord,phone);
    }
}
