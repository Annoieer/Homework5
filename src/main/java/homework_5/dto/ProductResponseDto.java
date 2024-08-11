package homework_5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductResponseDto {
    private BigDecimal totalSum;
    List<ProductDto> products;
}
