package com.example.mybatis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Menu {
    private String item;
    private String type;
    private int price;
    private int quantity;
    private int id;

    public Menu(String item, int price) {
        this.item = item;
        this.price = price;
    }

    public Menu(int id, String item, int price) {
        this.id = id;
        this.item = item;
        this.price = price;
    }
}
