package model.dao;
import java.sql.*;
import model.PaymentMethod;

public class PaymentMethodDBManager {

    private  Connection conn;

    // constructor 
    PaymentMethodDBManager(Connection conn){
        this.conn = conn;
    }

    // finds a payment method 
    public PaymentMethod findPaymentMethod(int paymentmethodID,int ORDERID)throws SQLException{

        String sql = "SELECT * paymentmethod WHERE paymentmethodID = ? AND ORDERID = ?";

        PreparedStatement query = conn.prepareStatement(sql);

        // for primitive potentialy null values need to receive using a wrapperclass to handle it here then conver to primitive if neccesary 
        

    }
    
}
