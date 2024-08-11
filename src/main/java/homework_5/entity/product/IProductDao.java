package homework_5.entity.product;

import java.util.ArrayList;

public interface IProductDao {

    Product getProductById(Long id);

    ArrayList<Product> getAllProducts();

    ArrayList<Product> getProductsByUserId(Long id);
}
