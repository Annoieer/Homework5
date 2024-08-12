package homework_5.service;

import homework_5.dto.ProductDto;
import homework_5.dto.ProductResponseDto;
import homework_5.entity.mapper.ProductMapper;
import homework_5.entity.product.IProductDao;
import homework_5.exceptions.CustomException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class ProductService {
    private final ProductMapper productMapper;
    private final IProductDao iProductDao;

    public ProductService(ProductMapper productMapper, IProductDao iProductDao) {
        this.productMapper = productMapper;
        this.iProductDao = iProductDao;
    }

    private final ArrayList<ProductDto> products = new ArrayList<>();

    @PostConstruct
    private void init() {
        iProductDao.getAllProducts()
                .forEach(product -> products.add(productMapper.toDto(product)));
    }

    public ProductDto getProduct(Long id) {
        try {
            return productMapper.toDto(iProductDao.getProductById(id));
        } catch (NullPointerException exception) {
            throw new CustomException("Продукт с id = " + id + " не существует", HttpStatus.NOT_FOUND);
        }
    }

    public ArrayList<ProductDto> getProductsByUserId(Long id) {
        try {
            ArrayList<ProductDto> productDtos = new ArrayList<>();
            iProductDao.getProductsByUserId(id)
                    .forEach(product -> productDtos.add(productMapper.toDto(product)));
            return productDtos;
        } catch (NullPointerException exception) {
            throw new CustomException("Пользователь с id = " + id + " не существует либо у него нет продуктов", HttpStatus.NOT_FOUND);
        }
    }

    public ProductResponseDto getAllProducts() {
        BigDecimal sum = new BigDecimal(0);
        for (ProductDto product : products) {
            sum = sum.add(product.getBalance());
        }
        return new ProductResponseDto(sum, products);
    }
}
