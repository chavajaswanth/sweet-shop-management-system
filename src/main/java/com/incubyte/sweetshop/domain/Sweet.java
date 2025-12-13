package com.incubyte.sweetshop.domain;

public class Sweet {

    private final String name;
    private final String category;
    private final double price;
    private int quantity;

    public Sweet(String name, String category, double price, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
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

    public double calculatePriceFor(int count) {
        return price * count;
    }




}
