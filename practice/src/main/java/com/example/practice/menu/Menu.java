package com.example.practice.menu;

public class Menu {
    private String item;
    private String type;
    private int price;
    private int quantity;
    private int id;


    public Menu(String item, int price, int id) {
        this.item = item;
        this.price = price;
        this.id = id;
    }

    public Menu(int quantity, int id) {
        this.id=id;
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
