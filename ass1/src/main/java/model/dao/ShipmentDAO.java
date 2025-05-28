package model.dao;

import model.Shipment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShipmentDAO {
    private Connection conn;

    public ShipmentDAO() {
        try {
            DBConnector db = new DBConnector();
            conn = db.openConnection();

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
        } catch (Exception e) {
            System.out.println("[ERROR] Could not initialise Shipment table:");
            e.printStackTrace();
        }
    }

    public void insertShipment(Shipment s) {
        String sql = "INSERT INTO Shipment (orderId, CUSTOMERID, address, shipmentDate, method, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getOrderId());
            stmt.setString(2, s.getCustomerId());
            stmt.setString(3, s.getAddress());
            stmt.setString(4, s.getDate());
            stmt.setString(5, s.getMethod());
            stmt.setString(6, s.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to insert shipment:");
            e.printStackTrace();
        }
    }

    public List<Shipment> getAllShipment(String customerId) {
        List<Shipment> list = new ArrayList<>();
        String sql = "SELECT * FROM Shipment WHERE CUSTOMERID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Shipment(
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
            System.out.println("[ERROR] Failed to fetch shipments:");
            e.printStackTrace();
        }
        return list;
    }

    public Shipment getShipmentById(int shipmentId) {
        String sql = "SELECT * FROM Shipment WHERE shipmentId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    public List<Shipment> searchShipment(String customerId, Integer shipmentId, String date, String orderId) {
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
        if (orderId != null && !orderId.isEmpty()) {
            sql.append(" AND orderId = ?");
            params.add(orderId);
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
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

    public List<String> getProductDetailsForShipment(String orderId) {
        List<String> productDetails = new ArrayList<>();
        String sql = "SELECT p.name, o.quantity, p.price " +
                "FROM CustomerOrder o " +
                "JOIN Product p ON o.PRODUCTID = p.productID " +
                "WHERE o.ORDERID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int qty = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String line = name + " (Qty: " + qty + ", $" + price + ")";
                productDetails.add(line);
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to get product details for shipment:");
            e.printStackTrace();
        }

        return productDetails;
    }

    public boolean isEditable(int shipmentId) {
        String sql = "SELECT status FROM Shipment WHERE shipmentId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getOrderId());
            stmt.setString(2, s.getCustomerId());
            stmt.setString(3, s.getAddress());
            stmt.setString(4, s.getDate());
            stmt.setString(5, s.getMethod());
            stmt.setString(6, s.getStatus());
            stmt.setInt(7, s.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to update Shipment:");
            e.printStackTrace();
        }
    }

    public void deleteShipment(int shipmentId) {
        String sql = "DELETE FROM Shipment WHERE shipmentId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, shipmentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to delete Shipment:");
            e.printStackTrace();
        }
    }
}