package com.example.assignmentwcd.model.category;

import com.example.assignmentwcd.entity.Category;

import java.util.List;

public interface CategoryModel {
    boolean save(Category obj);
    boolean update(int id, Category updateObj);

    boolean save(Category obj);

    boolean delete(int id);
    List<Category> findAll();
    Category findById(int id);
}
