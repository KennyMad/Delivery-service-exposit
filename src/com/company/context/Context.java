package com.company.context;

import com.company.facade.Facade;
import com.company.facade.impl.FacadeImpl;
import com.company.repository.CustomerDAO;
import com.company.repository.OrderDAO;
import com.company.repository.StoreDAO;
import com.company.repository.impl.CustomerDAOImpl;
import com.company.repository.impl.OrderDAOImpl;
import com.company.repository.impl.StoreDAOImpl;
import com.company.service.*;
import com.company.service.impl.*;
import com.company.ui.builder.MenuBuilder;
import com.company.ui.builder.impl.ConsoleMenuBuilder;
import com.company.ui.controller.MenuController;
import com.company.ui.controller.impl.MenuControllerImpl;

public class Context {

    private CustomerDAO customerDAO;
    private OrderDAO orderDAO;
    private StoreDAO storeDAO;

    private CustomerService customerService;
    private OrderService orderService;
    private StoreService storeService;
    private ProductService productService;

    private FileService fileService;

    private MenuController menuController;

    private Facade facade;

    public void initialize(){
        fileService = new FileServiceImpl();

        customerDAO = new CustomerDAOImpl(fileService);
        orderDAO = new OrderDAOImpl(fileService);
        storeDAO = new StoreDAOImpl(fileService);

        customerDAO.initialize();
        orderDAO.initialize();
        storeDAO.initialize();

        customerService = new CustomerServiceImpl(customerDAO);
        orderService = new OrderServiceImpl(customerDAO, orderDAO);
        storeService = new StoreServiceImpl(storeDAO);
        productService = new ProductServiceImpl(storeDAO);

        facade = new FacadeImpl(customerService,orderService, storeService,productService);

        MenuBuilder menuBuilder = new ConsoleMenuBuilder(facade);
        menuController = new MenuControllerImpl(menuBuilder.createMenu());
    }

    public Facade getFacade(){
        return facade;
    }

    public MenuController getMenuController(){
        return menuController;
    }
}
