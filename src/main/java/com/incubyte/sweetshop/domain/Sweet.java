package com.incubyte.sweetshop.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private double price;
    private int quantity;

    protected Sweet() {
        // required by JPA
    }

    public Sweet(String name, String category, double price, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double calculatePriceFor(int count) {
        validatePurchase(count);
        return price * count;
    }

    public void purchase(int count) {
        validatePurchase(count);
        this.quantity -= count;
    }

    private void validatePurchase(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Purchase quantity must be positive");
        }
        if (count > quantity) {
            throw new IllegalStateException("Insufficient stock");
        }
    }


}
