package model.dao;

import java.sql.Connection;

public class DB {
    protected String URL = "jdbc:sqlite:C:/IotBay/ass1/Iotbay.db";// identify the database url
    protected String driver = "org.sqlite.JDBC"; 
    protected Connection conn;
}
