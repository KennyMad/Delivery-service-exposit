package com.company.ui.menuItem.impl.storeItems.store;

import com.company.facade.Facade;
import com.company.ui.menuItem.MenuItem;

import java.util.Scanner;

public class RegisterStoreItem implements MenuItem {

    final Facade facade;

    public RegisterStoreItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Registration of a new store.");
    }

    @Override
    public void doAction() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.println("Description: ");
        String description = scanner.nextLine();

        facade.registerStore(name,description);

        System.out.println("Registered");
    }
}
