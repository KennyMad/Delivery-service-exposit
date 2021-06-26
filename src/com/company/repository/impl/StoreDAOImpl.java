package com.company.repository.impl;

import com.company.exception.WrongIdException;
import com.company.models.Customer;
import com.company.models.Store;
import com.company.repository.StoreDAO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StoreDAOImpl implements StoreDAO {

    private Map<Integer, Store> storeMap;

    @Override
    public void initialize(){
        Gson gson = new Gson();

        String jsonStore = "";
        String s = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("store.json"))){
            while ((s = reader.readLine()) != null)
                jsonStore += s;

            Type collectionType = new TypeToken<HashMap<Integer, Store>>(){}.getType();
            storeMap = gson.fromJson(jsonStore,collectionType);
        }
        catch (FileNotFoundException exception){
            storeMap = new HashMap<>();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void save(){
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter("store.json")){
            writer.write(gson.toJson(storeMap));
        }
        catch (IOException exception){
            exception.fillInStackTrace();
        }
    }

    @Override
    public Collection readAll() {
        return storeMap.values();
    }

    @Override
    public Store read(int id) throws WrongIdException {
        Store store = storeMap.get(id);

        if(store == null)
            throw new WrongIdException(id);

        return store;
    }

    @Override
    public void remove(int id) throws WrongIdException{
        if(storeMap.remove(id) == null)
            throw new WrongIdException(id);
    }

    @Override
    public void add(Store store) {
        storeMap.put(store.getId(),store);
    }

    @Override
    public int getFreeStoreId() {
        int id = storeMap.size();
        while (storeMap.containsKey(id))
            id++;
        return id;
    }

    @Override
    public int getFreeProductId(int storeId) {
        int id = storeMap.get(storeId).getProductList().size();
        while (storeMap.get(storeId).getProductList().containsKey(id))
            id++;
        return id;
    }
}
