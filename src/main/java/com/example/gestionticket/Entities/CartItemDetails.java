package com.example.gestionticket.Entities;

// CartItemDetails.java
public class CartItemDetails {
    private String titre;
    private String description;
    private int quantity;
    private double price;

    public CartItemDetails(String titre, String description, int quantity, double price) {
        this.titre = titre;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    // getters and setters...
}