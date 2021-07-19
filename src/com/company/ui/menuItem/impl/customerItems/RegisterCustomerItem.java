package com.company.ui.menuItem.impl.customerItems;

import com.company.exception.SaveDataException;
import com.company.facade.Facade;
import com.company.models.DTO.CustomerDTO;
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

            facade.registerCustomer(new CustomerDTO(name,additionalInformation));

            System.out.println("Registered.");
        }
        catch (InputMismatchException mismatchException){
            System.out.println("Invalid input.");
        }
        catch (SaveDataException exception){
            System.out.println(exception.getMessage());
        }
    }
}
