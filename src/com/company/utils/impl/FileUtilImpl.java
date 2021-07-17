package com.company.utils.impl;

import com.company.constants.Constants;
import com.company.exception.LoadDataException;
import com.company.exception.SaveDataException;
import com.company.models.Customer;
import com.company.models.Order;
import com.company.models.Store;
import com.company.repository.CustomerDAO;
import com.company.repository.DAO;
import com.company.repository.OrderDAO;
import com.company.repository.StoreDAO;
import com.company.repository.impl.CustomerDAOImpl;
import com.company.repository.impl.OrderDAOImpl;
import com.company.repository.impl.StoreDAOImpl;
import com.company.utils.FileUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileUtilImpl implements FileUtil {
    @Override
    public void save(Collection data, String fileName) throws SaveDataException {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter(fileName)){
            writer.write(gson.toJson(data));
        }
        catch (IOException exception){
            throw new SaveDataException(exception.getMessage());
        }
    }

    @Override
    public CustomerDAO loadCustomers() {
        List<Customer> customerList;
        try {
            customerList = load(Constants.CUSTOMERS_FILE, new TypeToken<ArrayList<Customer>>(){}.getType());
        }
        catch (FileNotFoundException | LoadDataException exception){
            customerList = new ArrayList<>();
        }
        return new CustomerDAOImpl(customerList);
    }

    @Override
    public OrderDAO loadOrders() {
        List<Order> orderList;
        try {
            orderList = load(Constants.ORDERS_FILE, new TypeToken<ArrayList<Order>>(){}.getType());
        }
        catch (FileNotFoundException | LoadDataException exception){
            orderList = new ArrayList<>();
        }
        return new OrderDAOImpl(orderList);
    }

    @Override
    public StoreDAO loadStores() {
        List<Store> storeList;
        try {
            storeList = load(Constants.STORE_FILE, new TypeToken<ArrayList<Store>>(){}.getType());
        }
        catch (FileNotFoundException | LoadDataException exception){
            storeList = new ArrayList<>();
        }
        return new StoreDAOImpl(storeList);
    }

    private List load (String fileName, Type type) throws FileNotFoundException, LoadDataException{
        Gson gson = new Gson();

        if (!new File(fileName).exists())
            throw new FileNotFoundException(fileName);

        StringBuilder jsonString = new StringBuilder();
        String s = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            while ((s = reader.readLine()) != null)
                jsonString.append(s);

            return gson.fromJson(jsonString.toString(),type);
        }
        catch (Exception exception) {
            throw new LoadDataException(fileName, exception.getMessage());
        }
    }
}
