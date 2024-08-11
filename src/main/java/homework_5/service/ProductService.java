package homework_5.service;

import homework_5.dto.ProductDto;
import homework_5.entity.product.IProductDao;
import homework_5.exceptions.CustomException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    private final IProductDao iProductDao;

    public ProductService(IProductDao iProductDao) {
        this.iProductDao = iProductDao;
    }

    private final ArrayList<ProductDto> products = new ArrayList<>();

    @PostConstruct
    private void init() {
        iProductDao.getAllProducts()
                .forEach(product -> products.add(new ProductDto(product)));
    }

    public ProductDto getProduct(Long id) {
        try {
            return new ProductDto(iProductDao.getProductById(id));
        } catch (NullPointerException exception) {
            throw new CustomException("Продукт с id = " + id + " не существует", HttpStatus.NOT_FOUND);
        }
    }

    public ArrayList<ProductDto> getProductsByUserId(Long id) {
        try {
            ArrayList<ProductDto> productDtos = new ArrayList<>();
            iProductDao.getProductsByUserId(id)
                    .forEach(product -> productDtos.add(new ProductDto(product)));
            return productDtos;
        } catch (NullPointerException exception) {
            throw new CustomException("Пользователь с id = " + id + " не существует либо у него нет продуктов", HttpStatus.NOT_FOUND);
        }
    }

    public ArrayList<ProductDto> getAllUsers() {
        return products;
    }
}
