package homework_5.dto;

import homework_5.entity.productType.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductTypeDto {
    private Long id;
    private String name;

    public ProductTypeDto(ProductType productType) {
        this.id = productType.getId();
        this.name = productType.getName();
    }
}
