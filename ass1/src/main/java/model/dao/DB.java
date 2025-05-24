package model.dao;

import java.sql.Connection;


public abstract class DB {
    protected String URL = "jdbc:sqlite:C:/IotBay/Iotbay.db";
    protected String driver = "org.sqlite.JDBC";
    protected Connection conn; 

}