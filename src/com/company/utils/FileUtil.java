package com.company.utils;

import com.company.exception.SaveDataException;
import com.company.repository.CustomerDAO;
import com.company.repository.DAO;
import com.company.repository.OrderDAO;
import com.company.repository.StoreDAO;

import java.util.Collection;

public interface FileUtil{

    void save(Collection data, String fileName) throws SaveDataException;

    CustomerDAO loadCustomers();

    OrderDAO loadOrders();

    StoreDAO loadStores();

}
