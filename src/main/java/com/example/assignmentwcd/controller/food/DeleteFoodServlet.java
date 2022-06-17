package com.example.assignmentwcd.controller.food;

import com.example.assignmentwcd.entity.Food;
import com.example.assignmentwcd.entity.myenum.FoodStatus;
import com.example.assignmentwcd.model.food.FoodModel;
import com.example.assignmentwcd.model.food.MySqlFoodModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteFoodServlet extends HttpServlet {
    private FoodModel foodModel;

    public DeleteFoodServlet() {
        this.foodModel = new MySqlFoodModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Food food = foodModel.findById(id);
        if (food == null) {
            req.getRequestDispatcher("/admin/view/page/404.jsp").forward(req, resp);
            return;
        }
        food.setStatus(FoodStatus.DELETED);
        try {
            if (foodModel.update(id, food)) {
                resp.sendRedirect("/admin/food/list");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
