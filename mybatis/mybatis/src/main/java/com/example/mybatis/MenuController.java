package com.example.mybatis;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuController {
    private MenuService service;
    public MenuController(MenuService service) {
        this.service = service;
    }

    @GetMapping("/create-menu-view")
    public String createItem(){
        return "menu/create.html";
    }
    @PostMapping("/create-menu")
    public String create(
            @RequestParam("item")
            String item,
            @RequestParam("price")
            int price,
            Model model
    ) {

        service.createItem(new Menu(item, price));
        return "redirect:/create-menu-view";
    }

    @GetMapping("/home-menu")
    public String allItems(Model model){

        model.addAttribute("menu",service.allItems() );
        return "menu/home.html";
    }

    @GetMapping("/{id}-menu")
    public String oneItem(
            @PathVariable("id")
            int id,
            Model model
    ){
        Menu item = service.readItem(id);
        model.addAttribute("item", item);
        return "menu/read.html";
    }


    @GetMapping("{id}-menu/update-menu-view")
    public String updateView(
            @PathVariable("id")
            int id,
            Model model){
        Menu item= service.readItem(id);
        model.addAttribute("item", item);
        return "menu/update.html";
    }

    @PostMapping("{id}-menu/update-menu")
    public String update(
            @PathVariable("id")
            int id,
            @RequestParam("item")
            String item,
            @RequestParam("price")
            int price
    ){
        service.updateItem(new Menu(id, item, price));
        return String.format("redirect:/%s-menu",id);
    }

    @GetMapping("{id}-menu/delete-menu-view")
    public String deleteView(
            @PathVariable("id")
            int id,
            Model model
    ){
        Menu item = service.readItem(id);
        model.addAttribute("item", item);
        return "menu/delete.html";
    }

    @PostMapping("{id}-menu/delete-menu")
    public String delete(
            @PathVariable("id")
            int id
    ){
        service.deleteItem(id);
        return "redirect:/home-menu";
    }

    //Order
    @GetMapping("{id}-menu/order-view")
    public String orderView(
            @PathVariable("id")
            int id,
            Model model
    ){
        Menu item= service.readItem(id);
        model.addAttribute("item", item);
        model.addAttribute("amountOfItem", item.getPrice()* item.getQuantity());
        return "menu/order-quantity.html";
    }
    @PostMapping("{id}-menu/order")
    public String order(
            @PathVariable("id")
            int id,
            @RequestParam("quantity")
            int quantity
    ){
        service.updateQuantity(id, quantity);
        return "redirect:/order";
//        return String.format("redirect:/%s-menu/order-view",id);
    }

    //Menu
    @GetMapping("/order")
    public String menu(Model model){
        model.addAttribute("menu",service.allItems() );
        model.addAttribute("totalQuantity", service.totalQuantity());

        model.addAttribute("total", service.total());
        return "menu/order.html";
    }

}
