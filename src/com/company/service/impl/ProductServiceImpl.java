package com.company.service.impl;

import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.models.DTO.ProductDTO;
import com.company.models.DTO.mapper.ProductMapper;
import com.company.models.Product;
import com.company.models.ProductAttribute;
import com.company.models.ProductCategory;
import com.company.models.Store;
import com.company.repository.ProductDAO;
import com.company.repository.StoreDAO;
import com.company.service.ProductService;
import com.company.utils.impl.SequenceGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    final private ProductDAO productDAO;
    final private StoreDAO storeDAO;

    public ProductServiceImpl (ProductDAO productDAO, StoreDAO storeDAO){
        this.productDAO = productDAO;
        this.storeDAO = storeDAO;
    }

    @Override
    public void add(ProductDTO productDTO) throws WrongIdException{
        Product product = ProductMapper.PRODUCT_MAPPER.productDTOtoProduct(productDTO);
        product.setId(SequenceGenerator.getFreeProductId(productDAO.readAll()));

        Store store = storeDAO.getById(product.getStoreId());
        if (store == null)
            throw new WrongIdException(product.getStoreId());
        store.getProductListIds().add(product.getId());

        productDAO.add(product);
    }


    @Override
    public void delete(int productId) throws WrongIdException{
        Product product = productDAO.remove(productId);
        if (product == null)
            throw new WrongIdException(productId);

        Store store = storeDAO.getById(product.getStoreId());
        if (store == null)
            throw new WrongIdException(product.getStoreId());
        store.getProductListIds().remove(productId);
    }

    @Override
    public void update(ProductDTO productDTO) throws WrongIdException{
        Product updatedProduct = ProductMapper.PRODUCT_MAPPER.productDTOtoProduct(productDTO);
        Product product = productDAO.getById(updatedProduct.getId());
        if (product == null)
            throw new WrongIdException(updatedProduct.getId());

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setAmount(updatedProduct.getAmount());
        product.setPrice(updatedProduct.getPrice());
        product.setCategories(updatedProduct.getCategories());
    }

    @Override
    public Collection<ProductDTO> getProductList() {
        return productDAO.readAll().stream()
                .map(ProductMapper.PRODUCT_MAPPER::productToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ProductDTO> getProductsByAttributes(Map<String,String> nameValueMap) throws InvalidAttributeException {
        Map<ProductAttribute, String> attributeValueMap = new HashMap<>();
        for (Map.Entry<String, String> entry : nameValueMap.entrySet()) {
            try {
                attributeValueMap.put(ProductAttribute.valueOf(entry.getKey().toUpperCase()), entry.getValue());
            }
            catch (IllegalArgumentException e){
                throw new InvalidAttributeException(entry.getValue());
            }
        }

        List<Product> productList = productDAO.readAll().stream()
                .filter(product -> isProductHasAttribute(product,attributeValueMap))
                .collect(Collectors.toList());

        return productList.stream()
                .map(ProductMapper.PRODUCT_MAPPER::productToProductDTO)
                .collect(Collectors.toList());
    }

    private boolean isProductHasAttribute(Product product, Map<ProductAttribute, String> attributeValueMap){
        for (Map.Entry<ProductAttribute, String> entry: attributeValueMap.entrySet()){
            switch (entry.getKey()){
                case PRICE:
                    if(!product.getPrice().toString().contains(entry.getValue().replace(",",".")))
                        return false;
                    break;
                case ID:
                    try {
                        if (product.getId() != Integer.parseInt(entry.getValue()))
                            return false;
                    }
                    catch (NumberFormatException exception){
                        return false;
                    }
                    break;
                case NAME:
                    if (!product.getName().toLowerCase().contains(entry.getValue().toLowerCase()))
                        return false;
                    break;
                case AMOUNT:
                    try {
                        if (product.getAmount() != Integer.parseInt(entry.getValue()))
                            return false;
                    }
                    catch (NumberFormatException exception){
                        return false;
                    }
                    break;
                case DESCRIPTION:
                    if (!product.getDescription().toLowerCase().contains(entry.getValue().toLowerCase()))
                        return false;
                    break;
                case STORE_ID:
                    try {
                        if (product.getStoreId() != Integer.parseInt(entry.getValue()))
                            return false;
                    }
                    catch (NumberFormatException exception){
                        return false;
                    }
                    break;
                case CATEGORY:
                    try {
                        if (!product.getCategories().contains(ProductCategory.valueOf(entry.getValue().toUpperCase())))
                            return false;
                    }
                    catch (IllegalArgumentException exception){
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    @Override
    public Collection<ProductDTO> getProductsByPrice(boolean reversed) {

        if (reversed)
            return getProductList().stream().sorted(Comparator.comparing(ProductDTO::getPrice).reversed()).collect(Collectors.toList());

        return getProductList().stream().sorted(Comparator.comparing(ProductDTO::getPrice)).collect(Collectors.toList());
    }

    @Override
    public Collection<ProductDTO> getProductsByCategory(ProductCategory category){
        return productDAO.readAll().stream()
                .filter(p -> p.getCategories().contains(category))
                .map(ProductMapper.PRODUCT_MAPPER::productToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ProductDTO> getProductsByStore(int storeId) throws WrongIdException {
        Store store = storeDAO.getById(storeId);
        if (store == null)
            throw new WrongIdException(storeId);

        List<Product> productList = new ArrayList<>();
        for (int id: store.getProductListIds())
            productList.add(productDAO.getById(id));
        return productList.stream()
                .map(ProductMapper.PRODUCT_MAPPER::productToProductDTO)
                .collect(Collectors.toList());
    }

}
