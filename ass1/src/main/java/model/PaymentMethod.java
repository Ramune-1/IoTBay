package model;
import java.time.*;

public class PaymentMethod {
    // define enum 
    public  enum PaymentOption{
        PAYPAL,
        CREDITCARD,
        APPLEPAY,
        DEBITCARD
        //GIFTCARD add potentially later if time 

    }


    private String OwnerName;

    // variable object of enum 

    private PaymentOption option;
    

    // credit card specific info 
    private LocalDate ExpDate;
    private String Cardnum;
    private int cvv;

    // digital paypal or applepay 
    private String accountname;


    // constructor creates payment option 
    // constructor below specific for credit cards 

    PaymentMethod(PaymentOption option,LocalDate ExpDate,String OwnerName, String Cardnum,int cvv){
        this.option = option;
        this.OwnerName  = OwnerName;
        this.ExpDate = ExpDate;
        this.Cardnum = Cardnum;
        this.cvv  = cvv;


    }

    // overload the constructor to cater to diffrent like paypal and applepay 
    
    PaymentMethod(PaymentOption option,String OwnerName,String accountname){
        this.option = option;
        this.OwnerName  = OwnerName;
        this.accountname = accountname;


    }

    




}
