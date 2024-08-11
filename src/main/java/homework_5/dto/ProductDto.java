package homework_5.dto;

import homework_5.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String account;
    private BigDecimal balance;
    private String productType;
    private String username;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.account = product.getAccount();
        this.balance = product.getBalance();
        this.productType = product.getProductType();
        this.username = product.getUsername();
    }
}
