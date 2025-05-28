package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.*;

import model.Customer;
import model.dao.CustomerDBManager;
import model.dao.DBConnector;

public class UserAccessTest {
    
    private CustomerDBManager customerManager;
    private DBConnector dbConnector;
    @BeforeEach
    public void setup() throws Exception{
        dbConnector  = new DBConnector();
        Connection conn = dbConnector.openConnection();
        customerManager = new CustomerDBManager(conn);
    }



     @AfterEach
    public void tearDown() throws Exception {
        dbConnector.closeConnection(); // cleanup
    }
     @Test
    public void testRegisterNewCustomerSuccess() throws Exception {
        String id = "D1001";
        String username = "user1";
        String name = "User1";
        String email = "user1@gmail.com";
        String password = "Test123!";
        String phone = "0411322333";
        String gender = "Male";

        
        if (customerManager.checkExistUsername(username)) return;

        customerManager.addCustomer(id, username, name, email, password, phone, gender);

        Customer customer = customerManager.findCustomer(username, password);
        assertNotNull(customer);
        assertEquals(username, customer.getUserName());
    }
     @Test
    public void testUsernameAlreadyExists() throws Exception {
        boolean exists = customerManager.checkExistUsername("user1");
        assertTrue(exists); 
    }
}
