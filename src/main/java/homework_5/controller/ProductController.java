package homework_5.controller;

import homework_5.dto.ProductDto;
import homework_5.dto.ProductResponseDto;
import homework_5.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/all")
    public ProductResponseDto getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/{productId}")
    public ProductDto getByProductId(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @GetMapping(value = "/byUser/{userId}")
    public ProductResponseDto getByUserId(@PathVariable Long userId) {
        ArrayList<ProductDto> products = productService.getProductsByUserId(userId);
        BigDecimal sum = new BigDecimal(0);
        for (ProductDto product : products) {
            sum = sum.add(product.getBalance());
        }
        return new ProductResponseDto(sum, products);
    }
}
