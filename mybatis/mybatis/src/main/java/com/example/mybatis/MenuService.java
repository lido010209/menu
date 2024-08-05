package com.example.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuService {
    private final SqlSessionFactory sessionFactory;
    public MenuService(SqlSessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public void createItem(Menu item){
        try (SqlSession session = sessionFactory.openSession()){
            MenuMapper mapper = session.getMapper(MenuMapper.class);
            mapper.createItem(item);
        }
    }

    public void updateItem(Menu item){
        try (SqlSession session = sessionFactory.openSession()){
            MenuMapper mapper = session.getMapper(MenuMapper.class);
            mapper.updateItem(item);
        }
    }

    public void deleteItem(int id){
        try (SqlSession session= sessionFactory.openSession()){
            MenuMapper mapper = session.getMapper(MenuMapper.class);
            mapper.deleteItem(id);
        }
    }


    public List<Menu> allItems(){
        try (SqlSession session= sessionFactory.openSession()){
            MenuMapper mapper= session.getMapper(MenuMapper.class);
            return mapper.allItems();
        }
    }

    public Menu readItem(int id){
        try (SqlSession session= sessionFactory.openSession()){
            MenuMapper mapper = session.getMapper(MenuMapper.class);
            return mapper.readItem(id);
        }
    }

    public void updateQuantity(int id, int quantity){
        try (SqlSession session= sessionFactory.openSession()){
            MenuMapper mapper = session.getMapper(MenuMapper.class);
            mapper.updateQuantity(id, quantity);
        }
    }

    public int totalQuantity(){
        int sum=0;
        for (Menu item: this.allItems()){
            sum+=item.getQuantity();
        }
        return sum;
    }
//    public Menu orderItem(int id, int quantity){
//        Menu oneItem=this.readItem(id);
//        this.updateQuantity(id, quantity);
//        return oneItem;
//    }

    public int total(){
        int sum=0;
        for (Menu item:this.allItems()){
            if (item.getQuantity()>0){
                sum+= item.getPrice()*item.getQuantity();
            }
        }
        return sum;
    }


}
