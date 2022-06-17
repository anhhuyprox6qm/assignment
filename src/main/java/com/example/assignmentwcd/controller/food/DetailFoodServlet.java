package com.example.assignmentwcd.controller.food;

import com.example.assignmentwcd.entity.Food;
import com.example.assignmentwcd.model.category.CategoryModel;
import com.example.assignmentwcd.model.category.MySqlCategoryModel;
import com.example.assignmentwcd.model.food.FoodModel;
import com.example.assignmentwcd.model.food.MySqlFoodModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetailFoodServlet extends HttpServlet {
    private CategoryModel categoryModel;
    private FoodModel foodModel;

    public DetailFoodServlet() {
        this.categoryModel = new MySqlCategoryModel();
        this.foodModel = new MySqlFoodModel();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Food food = foodModel.findById(id);
            if (food == null) {
                req.setAttribute("message", "Food is not found!");
                req.getRequestDispatcher("/admin/view/page/404.jsp").forward(req, resp);
            }
            req.setAttribute("food", food);
            req.setAttribute("categories", categoryModel.findAll());
            req.setAttribute("action", 2);
            req.getRequestDispatcher("/admin/view/food/create.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
