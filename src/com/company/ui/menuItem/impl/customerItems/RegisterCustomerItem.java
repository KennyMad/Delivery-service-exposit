package com.company.ui.menuItem.impl.customerItems;

import com.company.exception.WrongIdException;
import com.company.facade.Facade;
import com.company.ui.menuItem.MenuItem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RegisterCustomerItem implements MenuItem {

    final Facade facade;

    public RegisterCustomerItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Registration of a new client.");
    }

    @Override
    public void doAction() {
        String name, additionalInformation;

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Name: ");
            name = scanner.nextLine();

            System.out.print("Additional information: ");
            additionalInformation = scanner.nextLine();

            facade.registerCustomer(name,additionalInformation);

            System.out.println("Registered.");
        }
        catch (InputMismatchException mismatchException){
            System.out.println("Invalid input.");
        }
    }
}
