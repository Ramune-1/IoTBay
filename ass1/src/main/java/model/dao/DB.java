package model.dao;

import java.sql.Connection;

public class DB {
    protected String URL = "jdbc:sqlite:/Users/suqqie/IoTBay/IoTBay/Iotbay.db";// identify the database url
    protected String driver = "org.sqlite.JDBC"; 
    protected Connection conn;
}
