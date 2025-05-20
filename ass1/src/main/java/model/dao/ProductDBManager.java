package model.dao;
import model.Product;
import java.sql.*;

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
    
}
