package com.company.service.impl;

import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
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
    public void add(int storeId, String name, String description, int amount, double price, List<ProductCategory> categories) throws WrongIdException{
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setAmount(amount);
        product.setPrice(price);
        product.setCategories(categories);
        product.setId(SequenceGenerator.getFreeProductId(productDAO.readAll()));

        Store store = storeDAO.getById(storeId);
        if (store == null)
            throw new WrongIdException(storeId);
        store.getProductListIds().add(product.getId());
        product.setStoreId(storeId);

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
    public void update(int productId, String name, String description, int amount, double price, List<ProductCategory> categories) throws WrongIdException{
        Product product = productDAO.getById(productId);
        if (product == null)
            throw new WrongIdException(productId);

        product.setName(name);
        product.setDescription(description);
        product.setAmount(amount);
        product.setPrice(price);
        product.setCategories(categories);
    }

    @Override
    public Collection<Product> getProductList() {
        return productDAO.readAll();
    }

    @Override
    public Collection<Product> getProductsByAttributes(Map<String,String> nameValueMap) throws InvalidAttributeException {
        Map<ProductAttribute, String> attributeValueMap = new HashMap<>();
        for (Map.Entry<String, String> entry : nameValueMap.entrySet()) {
            try {
                attributeValueMap.put(ProductAttribute.valueOf(entry.getKey().toUpperCase()), entry.getValue());
            }
            catch (IllegalArgumentException e){
                throw new InvalidAttributeException(entry.getValue());
            }
        }

        List<Product> productList = getProductList().stream()
                .filter(product -> isProductHasAttribute(product,attributeValueMap))
                .collect(Collectors.toList());

        return productList;
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
    public Collection<Product> getProductsByPrice(boolean reversed) {

        if (reversed)
            return productDAO.readAll().stream().sorted(Comparator.comparing(Product::getPrice).reversed()).collect(Collectors.toList());

        return productDAO.readAll().stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
    }

    @Override
    public Collection<Product> getProductsByCategory(ProductCategory category){
        return getProductList().stream().filter(p -> p.getCategories().contains(category)).collect(Collectors.toList());
    }

    @Override
    public Collection<Product> getProductsByStore(int storeId) throws WrongIdException {
        Store store = storeDAO.getById(storeId);
        if (store == null)
            throw new WrongIdException(storeId);

        List<Product> productList = new ArrayList<>();
        for (int id: store.getProductListIds())
            productList.add(productDAO.getById(id));
        return productList;
    }

}
