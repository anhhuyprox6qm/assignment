package com.example.assignmentwcd.model.category;

import com.example.assignmentwcd.constant.SqlConstant;
import com.example.assignmentwcd.entity.Category;
import com.example.assignmentwcd.entity.myenum.CategoryStatus;
import com.example.assignmentwcd.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCategoryModel implements CategoryModel {
    @Override
    public boolean save(Category obj) {
        try{
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SqlConstant.CATEGORY_INSERT);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getStatus().getValue());
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(int id, Category updateObj) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.CATEGORY_UPDATE);
            preparedStatement.setString(1, updateObj.getName());
            preparedStatement.setInt(2, updateObj.getStatus().getValue());
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try{
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.CATEGORY_DELETE);
            preparedStatement.setInt(1, CategoryStatus.DELETED.getValue());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try{
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.CATEGORY_SELECT_ALL);
            preparedStatement.setInt(1, CategoryStatus.ACTIVE.getValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = convertResultSetToCategory(resultSet);
                if (category != null) {
                    categories.add(category);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    private Category convertResultSetToCategory(ResultSet resultSet) {
        try {
            int id = resultSet.getInt(SqlConstant.CATEGORY_FIELD_ID);
            String name = resultSet.getString(SqlConstant.CATEGORY_FIELD_NAME);
            int status = resultSet.getInt(SqlConstant.CATEGORY_FIELD_STATUS);
            return Category.CategoryBuilder.aCategory()
                    .withId(id)
                    .withName(name)
                    .withStatus(CategoryStatus.of(status))
                    .build();
        } catch (SQLException ex) {
            return null;
        }
    }
}
