package com.company.service.impl;

import com.company.exception.SaveDataException;
import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.models.Product;
import com.company.models.ProductCategory;
import com.company.models.Store;
import com.company.repository.ProductDAO;
import com.company.repository.StoreDAO;
import com.company.service.ProductService;
import com.company.utils.impl.SequenceGenerator;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.WrapDynaBean;

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
        List<Product> productList = new ArrayList<>();

        for (Product product: getProductList()) {
            DynaBean wrapper = new WrapDynaBean(product);
            boolean isFound = false;
            for (Map.Entry<String, String> entry: nameValueMap.entrySet()){
                try {
                    Object attribute = wrapper.get(entry.getKey().toLowerCase());

                    if (attribute.toString().toLowerCase().contains(entry.getValue().toLowerCase())) {
                        isFound = true;
                    } else {
                        isFound = false;
                        break;
                    }
                }
                catch (IllegalArgumentException exception){
                    throw new InvalidAttributeException(entry.getKey());
                }
            }
            if (isFound){
                productList.add(product);
            }
        }

        return productList;
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
