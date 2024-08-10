package com.example.CommercePlatform.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Basket {
    @Id
    @GeneratedValue
    long userId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Basket() {

    }

    public List<Product> getProducts() {
        return products;
    }

    public Basket(long userId, List<Product> products) {
        this.userId = userId;
        this.products = products;
    }
    public Basket(long userId) {
        this.userId = userId;
        products = new ArrayList<>();
    }

    public void removeProduct(Product product) {
        products.removeIf(p -> p.equals(product));
    }


    public void addProduct(Product product) {
        long id = product.getId();
        String name = product.getName();
        String description = product.getDescription();
        long amount = 1;
        double price = product.getPrice();

        Product productToAdd = new Product(id, name, description, amount, price);

        products.add(productToAdd);
    }

    public void removeProductById(long id) {
        products.removeIf(p -> p.getId() == id);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "userId=" + userId +
                ", products=" + products +
                '}';
    }
}
