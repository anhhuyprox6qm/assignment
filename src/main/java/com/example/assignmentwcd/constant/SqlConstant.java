package com.example.assignmentwcd.constant;

public class SqlConstant {
    public static final String CATEGORY_INSERT = "INSERT INTO categories (name, status) values (?, ?)";
    public static final String CATEGORY_UPDATE = "UPDATE categories set name = ?, status = ? WHERE id = ?";
    public static final String CATEGORY_DELETE = "UPDATE categories set status = ? WHERE id = ?";
    public static final String CATEGORY_SELECT_ALL = "SELECT * FROM categories WHERE status = ?";
    public static final String CATEGORY_FIELD_ID = "id";
    public static final String CATEGORY_FIELD_NAME = "name";
    public static final String CATEGORY_FIELD_STATUS = "status";

    public static final String FOOD_INSERT = "INSERT INTO foods (nameFood, categoryId, description, avatar, price, salesStartDate, updatedAt, status) values (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String FOOD_UPDATE = "UPDATE foods set nameFood = ?, categoryId = ?, description = ?, avatar = ?, price = ?, salesStartDate = ?, updatedAt = ?, status = ? WHERE id = ?";
    public static final String FOOD_DELETE = "UPDATE foods set status = ? WHERE id = ?";
    public static final String FOOD_FIELD_ID = "id";
    public static final String FOOD_FIELD_NAME = "nameFood";
    public static final String FOOD_FIELD_CATEGORY_ID = "categoryId";
    public static final String FOOD_FIELD_DESCRIPTION = "description";
    public static final String FOOD_FIELD_AVATAR = "avatar";
    public static final String FOOD_FIELD_PRICE = "price";
    public static final String FOOD_FIELD_SALES_START_DATE = "salesStartDate";
    public static final String FOOD_FIELD_UPDATED_AT = "updatedAt";
    public static final String FOOD_FIELD_STATUS = "status";
    public static final String FOOD_SELECT_BY_ID = "SELECT * FROM foods WHERE status = ? and id = ?";
    public static final String FOOD_SELECT_ALL = "SELECT * FROM foods WHERE status = ?";
}
