package homework_5.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;
    private String account;
    private BigDecimal balance;
    private Long productTypeId;
    private Long userId;
    private String productType;
    private String username;
}
