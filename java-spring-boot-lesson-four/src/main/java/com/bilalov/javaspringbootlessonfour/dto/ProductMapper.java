package com.bilalov.javaspringbootlessonfour.dto;

import com.bilalov.javaspringbootlessonfour.entities.Product;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDTO productDTO);

    List<Product> toProductList(List<ProductDTO> productDTOS);

    @InheritConfiguration
    ProductDTO fromProduct(Product product);

    List<ProductDTO> fromProductList(List<Product> products);
}
