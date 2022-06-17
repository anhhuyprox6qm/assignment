package com.example.assignmentwcd.model.food;

import com.example.assignmentwcd.constant.SqlConstant;
import com.example.assignmentwcd.entity.Food;
import com.example.assignmentwcd.entity.myenum.FoodStatus;
import com.example.assignmentwcd.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MySqlFoodModel implements FoodModel{
    @Override
    public boolean save(Food obj) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement
                    = connection.prepareStatement(SqlConstant.FOOD_INSERT);
            preparedStatement.setString(1, obj.getNameFood());
            preparedStatement.setInt(2, obj.getCategoryId());
            preparedStatement.setString(3, obj.getDescription());
            preparedStatement.setString(4, obj.getAvatar());
            preparedStatement.setDouble(5, obj.getPrice());
            preparedStatement.setString(6, obj.getSalesStartDate().toString());
            preparedStatement.setString(7, obj.getUpdatedAt().toString());
            preparedStatement.setInt(8, obj.getStatus().getValue());
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(int id, Food updateObj) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.FOOD_UPDATE);
            preparedStatement.setString(1, updateObj.getNameFood());
            preparedStatement.setInt(2, updateObj.getCategoryId());
            preparedStatement.setString(3, updateObj.getDescription());
            preparedStatement.setString(4, updateObj.getAvatar());
            preparedStatement.setDouble(5, updateObj.getPrice());
            preparedStatement.setString(6, updateObj.getSalesStartDate().toString());
            preparedStatement.setString(7, updateObj.getUpdatedAt().toString());
            preparedStatement.setInt(8, updateObj.getStatus().getValue());
            preparedStatement.setInt(9, id);
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.FOOD_DELETE);
            preparedStatement.setInt(1, FoodStatus.DELETED.getValue());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Food> findAll() {
        List<Food> foods = new ArrayList<>();
        try{
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.FOOD_SELECT_ALL);
            preparedStatement.setInt(1, FoodStatus.ARE_SELLING.getValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Food food = convertResultSetToFood(resultSet);
                if (food != null) {
                    foods.add(food);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foods;
    }

    @Override
    public Food findById(int id) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SqlConstant.FOOD_SELECT_BY_ID);
            preparedStatement.setInt(1, FoodStatus.ARE_SELLING.getValue());
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return convertResultSetToFood(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Food convertResultSetToFood(ResultSet resultSet) {
        try {
            int id = resultSet.getInt(SqlConstant.FOOD_FIELD_ID);
            String nameFood = resultSet.getString(SqlConstant.FOOD_FIELD_NAME);
            int categoryId = Integer.parseInt(resultSet.getString(SqlConstant.FOOD_FIELD_CATEGORY_ID));
            String description = resultSet.getString(SqlConstant.FOOD_FIELD_DESCRIPTION);
            String avatar = resultSet.getString(SqlConstant.FOOD_FIELD_AVATAR);
            double price = Double.parseDouble(resultSet.getString(SqlConstant.FOOD_FIELD_PRICE));
            LocalDateTime salesStartDate = resultSet.getTimestamp(SqlConstant.FOOD_FIELD_SALES_START_DATE).toLocalDateTime();
            LocalDateTime updatedAt = resultSet.getTimestamp(SqlConstant.FOOD_FIELD_UPDATED_AT).toLocalDateTime();
            int status = resultSet.getInt(SqlConstant.FOOD_FIELD_STATUS);
            return Food.FoodBuilder.aFood()
                    .withId(id)
                    .withNameFood(nameFood)
                    .withCategoryId(categoryId)
                    .withDescription(description)
                    .withAvatar(avatar)
                    .withPrice(price)
                    .withSalesStartDate(salesStartDate)
                    .withUpdatedAt(updatedAt)
                    .withStatus(FoodStatus.of(status))
                    .build();
        } catch (SQLException ex) {
            return null;
        }
    }
}
