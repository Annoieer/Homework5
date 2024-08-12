package homework_5.entity.productType;

import homework_5.connection.DataSource;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class ProductTypeDao implements IProductTypeDao {
    private final DataSource dataSource;

    private static final String READ_PRODUCT_TYPE_QUERY = "SELECT * FROM product_types WHERE id=?;";
    private static final String READ_ALL_PRODUCT_TYPE_QUERY = "SELECT * FROM product_types;";

    private ProductTypeDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ProductType getProductTypeById(@NotNull Long id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(READ_PRODUCT_TYPE_QUERY)) {
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            ArrayList<ProductType> productTypes = getProductTypesFromResultSet(resultSet);
            if (productTypes.size() != 0) {
                return productTypes.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("В таблице нет типа продукта с id = " + id);
        return null;
    }

    public ArrayList<ProductType> getAllProductTypes() {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(READ_ALL_PRODUCT_TYPE_QUERY)) {
            ResultSet resultSet = pst.executeQuery();
            ArrayList<ProductType> productTypes = getProductTypesFromResultSet(resultSet);
            if (productTypes.size() != 0) {
                return productTypes;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ошибка получения списка типов продуктов");
        return null;
    }

    private ArrayList<ProductType> getProductTypesFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<ProductType> productTypesById = new ArrayList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            ProductType productType = new ProductType(id, name);
            productTypesById.add(productType);
        }
        return productTypesById;
    }

}
