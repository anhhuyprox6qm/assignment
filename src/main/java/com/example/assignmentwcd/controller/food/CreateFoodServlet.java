package com.example.assignmentwcd.controller.food;

import com.example.assignmentwcd.entity.Category;
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
import java.util.List;

public class CreateFoodServlet extends HttpServlet {
    private CategoryModel categoryModel;
    private FoodModel foodModel;

    public CreateFoodServlet() {
        this.categoryModel = new MySqlCategoryModel();
        this.foodModel = new MySqlFoodModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = categoryModel.findAll();
        req.setAttribute("categories", categoryList);
        req.setAttribute("action", 1);
        req.getRequestDispatcher("/admin/view/food/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            String name = req.getParameter("nameFood");
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            String description = req.getParameter("description");
            String avatar = req.getParameter("avatar");
            double price = Double.parseDouble(req.getParameter("price"));
            int status = Integer.parseInt(req.getParameter("status"));
//        LocalDateTime salesStartDate = LocalDateTime.parse((req.getParameter("salesStartDate")), DateTimeFormatter.BASIC_ISO_DATE);

            Food food = new Food();
            food.setNameFood(name);
            food.setCategoryId(categoryId);
            food.setDescription(description);
            food.setAvatar(avatar);
            food.setPrice(price);
            food.setSalesStartDate(LocalDateTime.now());
            food.setUpdatedAt(LocalDateTime.now());
            food.setStatus(FoodStatus.of(status));
            if (food.isValid()) {
                boolean result = foodModel.save(food);
                if (result) {
                    resp.sendRedirect("/admin/food/list");
                } else {
                    try {
                        throw new Exception("Cant save food");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }else {
                req.setAttribute("food", food);
                req.setAttribute("action", 1);
                req.setAttribute("categories", categoryModel.findAll());
                req.setAttribute("errors", food.getErrors());
                req.getRequestDispatcher("/admin/view/food/create.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
