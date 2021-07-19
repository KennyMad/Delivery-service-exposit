package com.company.models.DTO.mapper;

import com.company.models.DTO.ProductDTO;
import com.company.models.Product;
import com.company.models.ProductCategory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-20T00:48:58+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO productToProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setCategoryList( productCategoryListToStringList( product.getCategories() ) );
        productDTO.setId( product.getId() );
        productDTO.setStoreId( product.getStoreId() );
        productDTO.setName( product.getName() );
        productDTO.setDescription( product.getDescription() );
        productDTO.setAmount( product.getAmount() );
        productDTO.setPrice( product.getPrice() );

        return productDTO;
    }

    @Override
    public Product productDTOtoProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategories( stringListToProductCategoryList( productDTO.getCategoryList() ) );
        product.setId( productDTO.getId() );
        product.setStoreId( productDTO.getStoreId() );
        product.setAmount( productDTO.getAmount() );
        product.setName( productDTO.getName() );
        product.setDescription( productDTO.getDescription() );
        if ( productDTO.getPrice() != null ) {
            product.setPrice( productDTO.getPrice() );
        }

        return product;
    }

    protected List<String> productCategoryListToStringList(List<ProductCategory> list) {
        if ( list == null ) {
            return null;
        }

        List<String> list1 = new ArrayList<String>( list.size() );
        for ( ProductCategory productCategory : list ) {
            list1.add( productCategory.name() );
        }

        return list1;
    }

    protected List<ProductCategory> stringListToProductCategoryList(List<String> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductCategory> list1 = new ArrayList<ProductCategory>( list.size() );
        for ( String string : list ) {
            list1.add( Enum.valueOf( ProductCategory.class, string ) );
        }

        return list1;
    }
}
