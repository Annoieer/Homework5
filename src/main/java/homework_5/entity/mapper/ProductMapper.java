package homework_5.entity.mapper;

import homework_5.dto.ProductDto;
import homework_5.entity.product.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductMapper() {
        this.modelMapper = new ModelMapper();

        TypeMap<ProductDto, Product> typeMap = modelMapper.createTypeMap(ProductDto.class, Product.class);
        typeMap.addMappings(mapper -> {
            mapper.map(ProductDto::getId, Product::setId);
            mapper.map(ProductDto::getAccount, Product::setAccount);
            mapper.map(ProductDto::getBalance, Product::setBalance);
            mapper.map(ProductDto::getProductType, Product::setProductType);
            mapper.map(ProductDto::getUsername, Product::setUsername);
        });
    }

    public ProductDto toDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}
