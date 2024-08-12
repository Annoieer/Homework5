package homework_5.entity.productType;

import java.util.ArrayList;

public interface IProductTypeDao {

    ProductType getProductTypeById(Long id);

    ArrayList<ProductType> getAllProductTypes();
}
