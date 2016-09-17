package com.csipon.kitchen.kitchen;

import com.csipon.kitchen.ConsoleHelper;
import com.csipon.kitchen.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private Tablet tablet;
    protected List<Dish> dishes;

    public Tablet getTablet() { return tablet; }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public int getTotalCookingTime() {
        int time = 0;
        for (Dish dish : dishes) {
            time += dish.getDuration();
        }
        return time;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    @Override
    public String toString() {
        return dishes.isEmpty() ? "" : "Your order: " + dishes + " of " + tablet;
    }
}
