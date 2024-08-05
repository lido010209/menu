package com.example.practice.menu;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    private final List<Menu> menu=new ArrayList<>();
    private int id=1;
    public static int totalQuantity=0;



    public Menu createItem(String item, int price) throws SQLException {
        Menu newItem= new Menu(item, price, id);
        menu.add(newItem);
        id++;
        return newItem;
    }

    public Menu updateItem(int id, String item, int price){
        int targetIdx=-1;
        for (int i = 0; i < menu.size(); i++) {
            Menu oneItem= menu.get(i);
            if (oneItem.getId()==id) {
                targetIdx=i;
                break;
            }
        }
        if (targetIdx!=-1){
            menu.get(targetIdx).setItem(item);
            menu.get(targetIdx).setPrice(price);
        }
        return menu.get(targetIdx);
    }

    public boolean deleteItem(int id){
        int targetIdx=-1;
        for (int i = 0; i < menu.size(); i++) {
            Menu item= menu.get(i);
            if (item.getId()==id){
                targetIdx=i;
                break;
            }
        }
        if (targetIdx!=-1){
            menu.remove(targetIdx);
            return true;
        }
        return false;
    }

    //Order
    public Menu orderItem(int id, int quantity){
        int targetIdx=-1;
        for (int i = 0; i < menu.size(); i++) {
            Menu item= menu.get(i);
            if (item.getId()==id){
                targetIdx=i;
                break;
            }
        }

        if (targetIdx!=-1){
            menu.get(targetIdx).setQuantity(quantity);
            this.totalQuantity+=quantity;
        }
        return menu.get(targetIdx);
    }

    public int total(){
        int sum=0;
        for (Menu item:menu){
            if (item.getQuantity()>0){
                sum+= item.getPrice()*item.getQuantity();
            }
        }
        return sum;
    }

    //Read all menu & one item
    public List<Menu> allItems(){
        return menu;
    }
    public Menu readItem(int id){
        for (Menu item: menu){
            if (item.getId()==id){
                return item;
            }
        }
        return null;
    }

//    //Order: quantity of items, add or remove
//    public Menu addNumItem(int id){
//        int targetIdx=-1;
//        for (int i = 0; i < menu.size(); i++) {
//            Menu item = menu.get(i);
//            if (item.getId()==id){
//                targetIdx=i;
//                break;
//            }
//        }
//        if (targetIdx!=-1){
//            int quantity=menu.get(targetIdx).getQuantity();
//            quantity++;
//            menu.get(targetIdx).setQuantity(quantity);
//        }
//        return menu.get(targetIdx);
//    }
//
//    public Menu subNumItem(int id){
//        int targetIdx=-1;
//        for (int i = 0; i < menu.size(); i++) {
//            Menu item = menu.get(i);
//            if (item.getId()==id){
//                targetIdx=i;
//                break;
//            }
//        }
//        if (targetIdx!=-1){
//            int quantity=menu.get(targetIdx).getQuantity();
//            if (quantity>0) quantity--;
//            menu.get(targetIdx).setQuantity(quantity);
//        }
//        return menu.get(targetIdx);
//    }



}
