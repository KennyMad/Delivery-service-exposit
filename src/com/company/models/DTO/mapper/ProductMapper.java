package com.company.models.DTO.mapper;

import com.company.models.DTO.ProductDTO;
import com.company.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "categories", target = "categoryList")
    ProductDTO productToProductDTO(Product product);

    @Mapping(source = "categoryList", target = "categories")
    Product productDTOtoProduct(ProductDTO productDTO);

}
