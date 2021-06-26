package com.company.ui.menuItem.impl.storeItems.product;

import com.company.exception.WrongIdException;
import com.company.facade.Facade;
import com.company.ui.menuItem.MenuItem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteProductItem implements MenuItem {

    final Facade facade;

    public DeleteProductItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Remove product.");
    }

    @Override
    public void doAction() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Store id: ");
            int storeId = scanner.nextInt();

            System.out.print("Product id: ");
            int id = scanner.nextInt();

            facade.deleteProduct(storeId,id);

            System.out.println("Deleted.");
        }
        catch (InputMismatchException mismatchException){
            System.out.println("Invalid input.");
        }
        catch (WrongIdException idException){
            System.out.println(idException.getMessage());
        }
    }
}
