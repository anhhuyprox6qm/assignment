package com.example.assignmentwcd.controller.food;

import com.example.assignmentwcd.entity.Food;
import com.example.assignmentwcd.entity.myenum.FoodStatus;
import com.example.assignmentwcd.model.category.CategoryModel;
import com.example.assignmentwcd.model.category.MySqlCategoryModel;
import com.example.assignmentwcd.model.food.FoodModel;
import com.example.assignmentwcd.model.food.MySqlFoodModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class UpdateFoodServlet extends HttpServlet {
    private CategoryModel categoryModel;
    private FoodModel foodModel;

    public UpdateFoodServlet() {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            String name = req.getParameter("nameFood");
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            int id = Integer.parseInt(req.getParameter("id"));
            String description = req.getParameter("description");
            String avatar = req.getParameter("avatar");
            double price = Double.parseDouble(req.getParameter("price"));
            int status = Integer.parseInt(req.getParameter("status"));
//        LocalDateTime salesStartDate = LocalDateTime.parse((req.getParameter("salesStartDate")), DateTimeFormatter.BASIC_ISO_DATE);
            Food food = foodModel.findById(id);
            if (food == null) {
                req.setAttribute("message", "Food is not found!");
                req.getRequestDispatcher("/admin/view/page/404.jsp").forward(req, resp);
            }
            food = new Food();
            food.setNameFood(name);
            food.setCategoryId(categoryId);
            food.setDescription(description);
            food.setAvatar(avatar);
            food.setPrice(price);
            food.setSalesStartDate(LocalDateTime.now());
            food.setUpdatedAt(LocalDateTime.now());
            food.setStatus(FoodStatus.of(status));
            if (food.isValid()) {
                boolean result = foodModel.update(id, food);
                if (result) {
                    resp.sendRedirect("/admin/food/list");
                } else {
                    try {
                        throw new Exception("Cant update food");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }else {
                req.setAttribute("food", food);
                req.setAttribute("action", 2);
                req.setAttribute("categories", categoryModel.findAll());
                req.setAttribute("errors", food.getErrors());
                req.getRequestDispatcher("/admin/view/food/create.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}
