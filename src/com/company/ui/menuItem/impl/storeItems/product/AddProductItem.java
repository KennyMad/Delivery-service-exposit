package com.company.ui.menuItem.impl.storeItems.product;

import com.company.exception.WrongIdException;
import com.company.facade.Facade;
import com.company.models.Product;
import com.company.models.ProductCategory;
import com.company.ui.menuItem.MenuItem;

import java.net.Proxy;
import java.util.*;

public class AddProductItem implements MenuItem {

    final Facade facade;

    public AddProductItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Add product.");
    }

    @Override
    public void doAction() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Store id: ");
            int id = scanner.nextInt();

            scanner.skip("\\R");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Description: ");
            String description = scanner.nextLine();

            System.out.print("Amount: ");
            int amount = scanner.nextInt();

            System.out.print("Price: ");
            double price = scanner.nextDouble();
            scanner.skip("\\R");

            facade.addProduct(id,name,description,amount,price,getCategories(scanner));

            System.out.println("Added.");
        }
        catch (InputMismatchException mismatchException){
            System.out.println("Invalid input.");
        }
        catch (WrongIdException exception){
            System.out.println(exception.getMessage());
        }
    }

    private List<ProductCategory> getCategories(Scanner scanner){

        List<ProductCategory> categories = new ArrayList<>();

        System.out.println("Categories:");
        for (ProductCategory category: ProductCategory.values()){
            System.out.println(category.toString().toLowerCase());
        }
        System.out.println("Enter categories (- if none):");

        String category;
        while (!(category = scanner.nextLine()).equals("-")){
            try {
                categories.add(ProductCategory.valueOf(category.toUpperCase()));
            }
            catch (IllegalArgumentException exception){
                System.out.println("Invalid input.");
            }
        }

        return categories;
    }
}
