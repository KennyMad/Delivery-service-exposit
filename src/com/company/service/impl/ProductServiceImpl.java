package com.company.service.impl;

import com.company.exception.SaveDataException;
import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.models.Product;
import com.company.models.ProductCategory;
import com.company.models.Store;
import com.company.repository.StoreDAO;
import com.company.service.ProductService;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.WrapDynaBean;

import java.util.*;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    final private StoreDAO storeDao;

    public ProductServiceImpl (StoreDAO storeDao){
        this.storeDao = storeDao;
    }

    @Override
    public void add(int storeId, String name, String description, int amount, double price, List<ProductCategory> categories) throws WrongIdException, SaveDataException {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setAmount(amount);
        product.setPrice(price);
        product.setCategories(categories);
        product.setId(storeDao.getFreeProductId(storeId));
        storeDao.read(storeId).getProductList().put(product.getId(),product);

        storeDao.save();
    }


    @Override
    public void delete(int storeId, int productId) throws WrongIdException, SaveDataException {
        storeDao.read(storeId).getProductList().remove(productId);

        storeDao.save();
    }

    @Override
    public void update(int storeId, int productId, String name, String description, int amount, double price, List<ProductCategory> categories) throws WrongIdException, SaveDataException {
        Product product = storeDao.read(storeId).getProductList().get(productId);
        product.setName(name);
        product.setDescription(description);
        product.setAmount(amount);
        product.setPrice(price);
        product.setCategories(categories);

        storeDao.save();
    }

    @Override
    public Collection<Product> getProductList() {
        Collection<Product> products = new ArrayList<>();
        storeDao.readAll().forEach(store -> products.addAll(store.getProductList().values()));
        return products;
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
        List <Product> productList = new ArrayList<>();
        storeDao.readAll().forEach(store -> productList.addAll(store.getProductList().values()));

        if (reversed)
            productList.sort(Comparator.comparing(Product::getPrice).reversed());
        else
            productList.sort(Comparator.comparing(Product::getPrice));

        return productList;
    }

    @Override
    public Collection<Product> getProductsByCategory(ProductCategory category){
        Collection<Product> products = new ArrayList<>();

        storeDao.readAll().forEach(store -> products.addAll(store.getProductList().values().stream().filter(product ->
                product.getCategories().contains(category)).collect(Collectors.toList())));

        return products;
    }

    @Override
    public Collection<Product> getProductsByStore(int storeId) throws WrongIdException {
        Store store = storeDao.read(storeId);
        if (store == null)
            throw new WrongIdException(storeId);

        return store.getProductList().values();
    }

}
