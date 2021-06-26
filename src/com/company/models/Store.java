package com.company.models;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class Store implements Cloneable{

    private int id;

    private HashMap<Integer ,Product> productList;

    private String name;

    private String description;

    public int getId() {
        return id;
    }

    public HashMap<Integer,Product> getProductList() {
        return productList;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductList(HashMap<Integer,Product> productList) {
        this.productList = productList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Store clone() throws CloneNotSupportedException{
        return (Store) super.clone();
    }

    public Store(){}

    @Override
    public String toString() {

        String productList = "";

        if (this.productList.values().size() != 0)
            productList += "\n";

        for (Product product: this.productList.values()){
            productList += product.toString();
            productList += "\n";
        }

        return "Store{" +
                "id=" + id +
                ", productList=[" + productList +
                "],\n name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
