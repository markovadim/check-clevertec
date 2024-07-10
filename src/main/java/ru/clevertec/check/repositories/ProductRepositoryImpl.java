package ru.clevertec.check.repositories;

import ru.clevertec.check.models.Product;
import ru.clevertec.check.utils.StringStorage;

import java.sql.*;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    private final String url;
    private final String username;
    private final String password;

    public ProductRepositoryImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Optional<Product> findById(int id) {
        Product product = null;
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(StringStorage.SQL_FIND_PRODUCT_BY_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int productId = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int quantityInStock = rs.getInt("quantity_in_stock");
                    boolean isWholesale = rs.getBoolean("is_wholesale");
                    product = new Product.Builder()
                            .id(productId)
                            .name(name)
                            .price(price)
                            .quantityInStock(quantityInStock)
                            .isWholesale(isWholesale)
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(product);
    }
}
