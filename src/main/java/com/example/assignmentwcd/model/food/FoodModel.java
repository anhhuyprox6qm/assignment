package com.example.assignmentwcd.model.food;

import com.example.assignmentwcd.entity.Food;

import java.util.List;

public interface FoodModel {
    boolean save(Food obj);
    boolean update(int id, Food updateObj);
    boolean delete(int id);
    List<Food> findAll();
    Food findById(int id);
}
