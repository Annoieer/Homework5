package homework_5.entity.product;

import homework_5.config.DataSourceConfig;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class ProductDao extends DataSourceConfig implements IProductDao {
    private static final String READ_PRODUCT_QUERY = "SELECT products.id, products.account, products.balance, " +
            "products.productTypeId, products.userId, users.username, product_types.name " +
            "FROM products " +
            "LEFT OUTER JOIN users " +
            "ON products.userId = users.id " +
            "LEFT OUTER JOIN product_types " +
            "ON products.productTypeId = product_types.id " +
            "WHERE products.id=?;";
    private static final String READ_ALL_PRODUCT_QUERY = "SELECT products.id, products.account, products.balance, " +
            "products.productTypeId, products.userId, users.username, product_types.name " +
            "FROM products " +
            "LEFT OUTER JOIN users " +
            "ON products.userId = users.id " +
            "LEFT OUTER JOIN product_types " +
            "ON products.productTypeId = product_types.id;";
    private static final String READ_PRODUCT_BY_USER_QUERY = "SELECT products.id, products.account, products.balance, " +
            "products.productTypeId, products.userId, users.username, product_types.name " +
            "FROM products " +
            "LEFT OUTER JOIN users " +
            "ON products.userId = users.id " +
            "LEFT OUTER JOIN product_types " +
            "ON products.productTypeId = product_types.id " +
            "WHERE userId=?;";

    public Product getProductById(@NotNull Long id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(READ_PRODUCT_QUERY)) {
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            ArrayList<Product> products = getProductFromResultSet(resultSet);
            if (products.size() != 0) {
                return products.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("В таблице нет продукта с id = " + id);
        return null;
    }

    public ArrayList<Product> getAllProducts() {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(READ_ALL_PRODUCT_QUERY)) {
            ResultSet resultSet = pst.executeQuery();
            ArrayList<Product> products = getProductFromResultSet(resultSet);
            if (products.size() != 0) {
                return products;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ошибка получения списка продуктов");
        return null;
    }

    public ArrayList<Product> getProductsByUserId(@NotNull Long id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(READ_PRODUCT_BY_USER_QUERY)) {
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            ArrayList<Product> products = getProductFromResultSet(resultSet);
            if (products.size() != 0) {
                return products;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ошибка получения списка продуктов");
        return null;
    }

    private ArrayList<Product> getProductFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Product> productsById = new ArrayList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String account = resultSet.getString("account");
            BigDecimal balance = resultSet.getBigDecimal("balance");
            Long productTypeId = resultSet.getLong("productTypeId");
            Long userId = resultSet.getLong("userId");
            String productType = resultSet.getString("name");
            String username = resultSet.getString("username");
            Product product = new Product(id, account, balance, productTypeId, userId, productType, username);
            productsById.add(product);
        }
        return productsById;
    }

}
