package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.*;

import model.Customer;
import model.CustomerLog;
import model.dao.CustomerAccessLogDBManager;
import model.dao.CustomerDBManager;
import model.dao.DBConnector;

public class UserAccessTest {
    private CustomerAccessLogDBManager customerLogManager;
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
    public void testCustomerLoginSuccess() throws Exception {
        Customer customer = customerManager.findCustomer("user1", "Test123!");
        assertNotNull(customer);
        assertEquals("user1", customer.getUserName());
    }

     @Test
    public void testGetCustomerByID() throws SQLException {
        
         Customer customer = customerManager.findCustomer("user1", "Test123!");

        assertNotNull(customer);
        assertEquals("D1001", customer.getCustomerID());
        assertEquals("user1", customer.getUserName()); // adjust to your test data
        assertEquals("User1", customer.getName());
        assertEquals("user1@gmail.com", customer.getGmail());
        assertEquals("0411322333", customer.getPhone());
        assertEquals("Male", customer.getGender());
    }
   /*@Test
    public void testUpdateCustomerInfo() throws Exception {
     // Use an actual customer ID from your test DB
    String newUsername = "updatedUser";
    String newEmail = "updateduser@gmail.com";
    String newPassword = "Updated123!";
    String newPhone = "0411000000";
    String newGender = "Male";

    // Perform update
    customerManager.updateCustomer("1579832c-88b0-4e3d-b0c5-23568cba5558", newUsername, "Updated Name", newEmail, newPassword, newPhone, newGender);

    // Fetch and verify
    Customer updated = customerManager.findCustomer(newUsername, newPassword);
    assertNotNull(updated);
    assertEquals("updatedUser", updated.getUserName());
    assertEquals("0411000000", updated.getPhone());
}
    @Test
    public void testFindAllCustomerLogs() throws Exception {
        String customerId = "trangiaiduong"; // use a real ID from DB
        List<CustomerLog> logs = customerLogManager.findAllLog(customerId);
        assertNotNull(logs);
        assertTrue(logs.size() >= 0); // Can be 0 if no logs
    }*/
}

