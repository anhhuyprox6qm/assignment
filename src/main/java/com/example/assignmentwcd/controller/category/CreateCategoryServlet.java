package com.example.assignmentwcd.controller.category;

import com.example.assignmentwcd.entity.Category;
import com.example.assignmentwcd.entity.myenum.CategoryStatus;
import com.example.assignmentwcd.model.category.CategoryModel;
import com.example.assignmentwcd.model.category.MySqlCategoryModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCategoryServlet extends HttpServlet {
    private CategoryModel categoryModel;

    public CreateCategoryServlet() {
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/view/category/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        Category category = new Category();
        category.setName(name);
        category.setStatus(CategoryStatus.ACTIVE);
        if (!category.isValid()) {
            req.setAttribute("errors", category.getErrors());
            req.getRequestDispatcher("/admin/view/category/create.jsp").forward(req, resp);
            return;
        }
        boolean result = categoryModel.save(category);
        if (result) {
            resp.sendRedirect("/admin/category/list");
        }
        System.out.println(result);
    }
}
