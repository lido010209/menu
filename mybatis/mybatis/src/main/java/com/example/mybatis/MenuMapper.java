package com.example.mybatis;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuMapper {
    @Insert("""
            INSERT INTO menu(item, price)
            VALUES (#{item},#{price});
            """)
    void createItem(Menu item);

    @Select(" SELECT * FROM menu;")
    List<Menu> allItems();

    @Select("""
            SELECT * FROM menu
            WHERE id=#{id};
            """)
    Menu readItem(int id);

    @Update("""
            UPDATE menu
            SET item=#{item}, price=#{price}
            WHERE id= #{id};
            """)
    void updateItem(Menu item);

    @Update("""
            UPDATE menu
            SET quantity=#{quantity}
            WHERE id= #{id};
            """)
    void updateQuantity(int id, int quantity);

    @Delete("""
            DELETE FROM menu
            WHERE id = #{id};
            """)
    void deleteItem(int id);


}
