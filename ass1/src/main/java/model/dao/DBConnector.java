package model.dao;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

public class DBConnector extends DB {// use to 
    
    public DBConnector() throws ClassNotFoundException , SQLException{
            Class.forName(driver);// one of two way to register driver other ways is using registerDriver()
            conn = DriverManager.getConnection(URL);
    }

    public Connection openConnection(){
        return this.conn;
    }

    public void closeConnection() throws SQLException{
        this.conn.close();
    }


}
