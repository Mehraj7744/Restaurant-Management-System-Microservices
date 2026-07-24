package com.restaurant.menu;
import com.restaurant.menu.entity.MenuItem;

public class Test {

    public static void main(String[] args) {

        MenuItem item = MenuItem.builder().build();

        System.out.println(item);
    }
}