package homework_5.service;

import homework_5.dto.ProductTypeDto;
import homework_5.dto.ProductTypeResponseDto;
import homework_5.entity.productType.IProductTypeDao;
import homework_5.exceptions.CustomException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductTypeService {

    private final IProductTypeDao iProductTypeDao;
    private final ArrayList<ProductTypeDto> types = new ArrayList<>();

    @PostConstruct
    private void init() {
        iProductTypeDao.getAllProductTypes()
                .forEach(productType -> types.add(new ProductTypeDto(productType)));
    }

    public ProductTypeService(IProductTypeDao iProductTypeDao) {
        this.iProductTypeDao = iProductTypeDao;
    }

    public ProductTypeDto getProductType(Long id) {
        try {
            return new ProductTypeDto(iProductTypeDao.getProductTypeById(id));
        } catch (NullPointerException exception) {
            throw new CustomException("Тип продукта с id = " + id + " не существует", HttpStatus.NOT_FOUND);
        }
    }

    public ProductTypeResponseDto getAllProductTypes() {
        return new ProductTypeResponseDto(types.size(), types);
    }
}
