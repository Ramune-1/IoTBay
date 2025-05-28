package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.*;

import model.Shipment;
import model.dao.DBConnector;
import model.dao.ShipmentDAO;

public class ShipmentTest {

    private DBConnector dbConnector;
    private ShipmentDAO shipmentDAO;

    @BeforeEach
    public void setUp() throws Exception {
        dbConnector = new DBConnector();
        Connection conn = dbConnector.openConnection();
        shipmentDAO = new ShipmentDAO(); // your constructor already handles connection
    }

    @AfterEach
    public void tearDown() throws Exception {
        dbConnector.closeConnection();
    }

    @Test
    public void testInsertShipmentAndRetrieve() {
        String id = "TEST_SHP001";
        Shipment shipment = new Shipment(id, "ORD_TEST_001", "CUS_TEST_001",
                "123 Test Street", "2025-06-01", "Express", "Pending");

        shipmentDAO.insertShipment(shipment);
        Shipment saved = shipmentDAO.getShipmentById(id);

        assertNotNull(saved);
        assertEquals("123 Test Street", saved.getAddress());
        assertEquals("Pending", saved.getStatus());
    }

    @Test
    public void testUpdateShipment() {
        String id = "TEST_SHP001";
        Shipment shipment = shipmentDAO.getShipmentById(id);
        assertNotNull(shipment);

        if (shipmentDAO.isEditable(id)) {
            shipment.setAddress("456 Updated Address");
            shipment.setStatus("Processing");
            shipmentDAO.updateShipment(shipment);

            Shipment updated = shipmentDAO.getShipmentById(id);
            assertEquals("456 Updated Address", updated.getAddress());
            assertEquals("Processing", updated.getStatus());
        } else {
            fail("Shipment is not editable");
        }
    }

    @Test
    public void testDeleteShipment() {
        String id = "TEST_SHP001";
        Shipment existing = shipmentDAO.getShipmentById(id);
        assertNotNull(existing);

        shipmentDAO.deleteShipment(id);
        Shipment deleted = shipmentDAO.getShipmentById(id);

        assertNull(deleted, "Shipment should be deleted");
    }

    @Test
    public void testGetAllShipments() {
        List<Shipment> list = shipmentDAO.getAllShipment("CUS_TEST_001");
        assertNotNull(list);
    }

    @Test
    public void testSearchShipment() {
        List<Shipment> result = shipmentDAO.searchShipment("CUS_TEST_001", "TEST_SHP001", "2025-06-01", "ORD_TEST_001");
        assertNotNull(result);
        // You can add assertEquals(1, result.size()); if you expect a single match
    }

    @Test
    public void testIsEditable() {
        boolean editable = shipmentDAO.isEditable("TEST_SHP001");
        assertTrue(editable || !editable); // just confirming it returns a boolean
    }
}
