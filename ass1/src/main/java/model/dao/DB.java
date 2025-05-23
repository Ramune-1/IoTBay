
package model.dao; 

import java.sql.Connection;

/** 
* Super class of DAO classes that stores the database information 
*  
*/

public abstract class DB {   

protected String URL = "jdbc:postgresql://localhost:5432/";
 
protected String db = "labs";//name of the database   
protected String dbuser = "ramune";//db root user   
protected String dbpass = "234968"; //db root password   
protected String driver = "org.postgresql.Driver"; //jdbc client driver - built in with NetBeans   
protected Connection conn; //connection null-instance to be initialized in sub-classes

}
