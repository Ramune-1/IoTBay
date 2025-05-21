package model.dao;
import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDBManager {
    private Connection conn;

    public ProductDBManager(Connection conn) throws SQLException{
        this.conn = conn;
    }

    public Product findProductByID(int productID) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Product WHERE productID = ?");
        ps.setInt(1, productID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String name = rs.getString("name");
            String description = rs.getString("description");
            double price = rs.getDouble("price");
            int stock = rs.getInt("stock");
            return new Product(productID, name, stock, price, description);
        }
        return null;
    }

    public void addProduct(Product product) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Product (name, quantity, price, description) VALUES (?, ?, ?, ?)");
        ps.setString(1, product.getName());
        ps.setInt(4, product.getQuantity());
        ps.setDouble(3, product.getPrice());
        ps.setString(2, product.getDescription());
        ps.executeUpdate();
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product";
        try (Statement sm = conn.createStatement();
            ResultSet rs = sm.executeQuery(query)) {
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("productID"),
                    rs.getString("name"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("description")
                );
                products.add(product);
            }
        }
        return products;
    }
    
}
