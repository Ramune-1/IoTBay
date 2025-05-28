package model.dao;

import model.Shipment;
import java.sql.*;  // and other necessary imports
import java.util.ArrayList;
import java.util.List;


public class ShipmentDAO {
    private String jdbcURL = "jdbc:sqlite:C:/Users/pc/JavaProjects/ShipmentApp/Iotbay.db";

    public ShipmentDAO() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(jdbcURL)) {
                String sql = "CREATE TABLE IF NOT EXISTS Shipment (" +
                        "shipmentId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "orderId VARCHAR(36) NOT NULL, " +
                        "CUSTOMERID VARCHAR(36) NOT NULL, " +
                        "address TEXT NOT NULL, " +
                        "shipmentDate TEXT NOT NULL, " +
                        "method TEXT NOT NULL, " +
                        "status TEXT NOT NULL)";
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Could not initialise database:");
            e.printStackTrace();
        }
    }

    public void insertShipment(Shipment s) {
        String sql = "INSERT INTO Shipment (orderId, CUSTOMERID, address, shipmentDate, method, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(jdbcURL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getOrderId());
            stmt.setString(2, s.getCustomerId());
            stmt.setString(3, s.getAddress());
            stmt.setString(4, s.getDate());
            stmt.setString(5, s.getMethod());
            stmt.setString(6, s.getStatus());
            stmt.executeUpdate();
            System.out.println("[DEBUG] Shipment inserted successfully.");
        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to insert shipment:");
            e.printStackTrace();
        }
    }

    public List<Shipment> getAllShipment(String customerId) {
        List<Shipment> list = new ArrayList<>();
        String sql = "SELECT * FROM Shipment WHERE CUSTOMERID = ?";
        try (Connection conn = DriverManager.getConnection(jdbcURL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Shipment s = new Shipment(
                    rs.getInt("shipmentId"),
                    rs.getString("orderId"),
                    rs.getString("CUSTOMERID"),
                    rs.getString("address"),
                    rs.getString("shipmentDate"),
                    rs.getString("method"),
                    rs.getString("status")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to fetch shipments");
            e.printStackTrace();
        }

        return list;
    }

    public Shipment getShipmentById(int shipmentId) {
        String sql = "SELECT * FROM Shipment WHERE shipmentId = ?";
        try (Connection conn = DriverManager.getConnection(jdbcURL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, shipmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Shipment(
                    rs.getInt("shipmentId"),
                    rs.getString("orderId"),
                    rs.getString("CUSTOMERID"),
                    rs.getString("address"),
                    rs.getString("shipmentDate"),
                    rs.getString("method"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to fetch shipment by ID:");
            e.printStackTrace();
        }
        return null;
    }

    public List<Shipment> searchShipment(String customerId, Integer shipmentId, String date) {
        List<Shipment> results = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Shipment WHERE CUSTOMERID = ?");
        List<Object> params = new ArrayList<>();
        params.add(customerId);

        if (shipmentId != null) {
            sql.append(" AND shipmentId = ?");
            params.add(shipmentId);
        }
        if (date != null && !date.isEmpty()) {
            sql.append(" AND shipmentDate = ?");
            params.add(date);
        }

        try (Connection conn = DriverManager.getConnection(jdbcURL);
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                if (params.get(i) instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) params.get(i));
                } else {
                    stmt.setString(i + 1, params.get(i).toString());
                }
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(new Shipment(
                    rs.getInt("shipmentId"),
                    rs.getString("orderId"),
                    rs.getString("CUSTOMERID"),
                    rs.getString("address"),
                    rs.getString("shipmentDate"),
                    rs.getString("method"),
                    rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to search shipments:");
            e.printStackTrace();
        }

        return results;
    }

    public boolean isEditable(int shipmentId) {
        String sql = "SELECT status FROM Shipment WHERE shipmentId = ?";
        try (Connection conn = DriverManager.getConnection(jdbcURL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, shipmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return !rs.getString("status").equalsIgnoreCase("Finalised");
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to check editable status:");
            e.printStackTrace();
        }
        return false;
    }

    public void updateShipment(Shipment s) {
        String sql = "UPDATE Shipment SET orderId = ?, CUSTOMERID = ?, address = ?, shipmentDate = ?, method = ?, status = ? WHERE shipmentId = ?";
        try (Connection conn = DriverManager.getConnection(jdbcURL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getOrderId());
            stmt.setString(2, s.getCustomerId());
            stmt.setString(3, s.getAddress());
            stmt.setString(4, s.getDate());
            stmt.setString(5, s.getMethod());
            stmt.setString(6, s.getStatus());
            stmt.setInt(7, s.getId());
            int rows = stmt.executeUpdate();
            System.out.println("[DEBUG] Updated rows: " + rows);
        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to update Shipment:");
            e.printStackTrace();
        }
    }

    public void deleteShipment(int shipmentId) {
        String sql = "DELETE FROM Shipment WHERE shipmentId = ?";
        try (Connection conn = DriverManager.getConnection(jdbcURL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, shipmentId);
            int rows = stmt.executeUpdate();
            System.out.println("[DEBUG] Deleted rows: " + rows);
        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to delete Shipment:");
            e.printStackTrace();
        }
    }
}
