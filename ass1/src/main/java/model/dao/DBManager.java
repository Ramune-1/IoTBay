package model.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private Connection conn;
    private CustomerDBManager customer;

    public DBManager(Connection conn) throws SQLException {
        this.conn = conn;
        customer = new CustomerDBManager(conn);
    }

    public CustomerDBManager getCustomerDBManager(){
        return  customer;
    }

}
