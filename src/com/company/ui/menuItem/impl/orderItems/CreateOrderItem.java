package com.company.ui.menuItem.impl.orderItems;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.facade.Facade;
import com.company.models.OrderAddress;
import com.company.models.Product;
import com.company.ui.menuItem.MenuItem;

import java.util.*;

public class CreateOrderItem implements MenuItem {

    final Facade facade;

    public CreateOrderItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Take an order.");
    }

    @Override
    public void doAction() {

        Scanner scanner = new Scanner(System.in);

        try {
            int customerId;

            System.out.print("Customer id: ");
            customerId = scanner.nextInt();
            scanner.skip("\\R");

            HashMap<Integer, List<Product>> productsByStoreId = getProducts(scanner);

            OrderAddress orderAddress = new OrderAddress();

            System.out.print("House: ");
            orderAddress.setHouse(scanner.nextLine());

            System.out.print("Street: ");
            orderAddress.setStreet(scanner.nextLine());

            System.out.print("City: ");
            orderAddress.setCity(scanner.nextLine());

            facade.createOrder(customerId,productsByStoreId, orderAddress);

            System.out.println("Created.");
        }
        catch (InputMismatchException | IndexOutOfBoundsException | NumberFormatException mismatchException){
            System.out.println("Invalid input.");
        }
        catch (WrongIdException | SaveDataException exception){
            System.out.println(exception.getMessage());
        }
    }

    private HashMap<Integer,List<Product>> getProducts(Scanner scanner) throws WrongIdException, InputMismatchException, IndexOutOfBoundsException, NumberFormatException{
        int storeId;

        HashMap<Integer, List<Product>> productsByStoreId = new HashMap();

        System.out.print("Enter store id (- if none): ");
        String input = scanner.nextLine();
        while (!input.equals("-")){
            storeId = Integer.parseInt(input);

            System.out.println("Products: ");
            facade.getProductsByStore(storeId).forEach(System.out::println);

            productsByStoreId.put(storeId,getProducts(scanner,storeId));

            System.out.print("Enter store id (- if none): ");
            input = scanner.nextLine();
        }


        return productsByStoreId;
    }

    private List<Product> getProducts(Scanner scanner, int storeId) throws WrongIdException{
        List<Product> productList = new ArrayList<>();
        boolean added;
        int id;
        String input;

        System.out.print("Enter product id (- if none): ");
        input = scanner.nextLine();

        while (!input.equals("-")){
            id = Integer.parseInt(input);

            added = false;

            for (Product product: facade.getProductsByStore(storeId)){
                if (product.getId() == id){
                    System.out.print("Amount: ");
                    try {
                        Product productToAdd = product.clone();
                        productToAdd.setAmount(Integer.parseInt(scanner.nextLine()));
                        productList.add(productToAdd);
                    }
                    catch (CloneNotSupportedException ignored){}

                    added = true;
                    break;
                }
            }
            if (!added)
                System.out.println("Wrong id.");

            input = scanner.nextLine();
        }

        return productList;
    }
}
