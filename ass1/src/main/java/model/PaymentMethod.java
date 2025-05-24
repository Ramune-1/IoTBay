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
    private int paymentmethodID;

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

    PaymentMethod(PaymentOption option,LocalDate ExpDate,String OwnerName, String Cardnum,int cvv,int paymentmethodID){
        this.option = option;
        this.OwnerName  = OwnerName;
        this.ExpDate = ExpDate;
        this.Cardnum = Cardnum;
        this.cvv  = cvv;
        this.paymentmethodID = paymentmethodID;


    }

    // overload the constructor to cater to diffrent like paypal and applepay 
    
    PaymentMethod(PaymentOption option,String OwnerName,String accountname,int paymentmethodID){
        this.option = option;
        this.OwnerName  = OwnerName;
        this.accountname = accountname;
        this.paymentmethodID = paymentmethodID;


    }

    // getters 

    public PaymentOption getPaymentOption(){
        return  option;
    }

    public String getOwnerName(){
        return OwnerName;
    }


    public int getPaymentMethodID(){
            return paymentmethodID;
    }

    public LocalDate getExpdate(){
        return ExpDate;
    }

    public String getcardnum(){
        return Cardnum;
    }


    public int getcvv(){
        return cvv;
    }

    public String getaccname(){
        return accountname;
    }
     

 // setters 

    public void setPaymentOption(PaymentOption option){
        this.option = option;
    }

    public void setOwnerName(String OwnerName){
        this.OwnerName = OwnerName;
    }


    public void setPaymentMethodID(int paymentmethodID){
            this.paymentmethodID = paymentmethodID;
    }

    public void setExpdate(LocalDate ExpDate){
        this.ExpDate = ExpDate;
    }

    public void setcardnum(String Cardnum){
        this.Cardnum = Cardnum;
    }


    public void setcvv(int cvv){
        this.cvv = cvv;
    }

    public void setaccname(String accountname){
        this.accountname = accountname;
    }


    
    




}
