package com.example.assignmentwcd.entity;

import com.example.assignmentwcd.entity.myenum.FoodStatus;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Food {
    private int id;
    private String nameFood;
    private int categoryId;
    private String description;
    private String avatar;
    private double price;
    private LocalDateTime salesStartDate;
    private LocalDateTime updatedAt;
    private FoodStatus status;

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }

    private HashMap<String, String> errors = new HashMap<>();

    public boolean isValid() {
        checkValid();
        return errors.size() == 0;
    }
    private void checkValid() {
        if (nameFood == null || nameFood.length() == 0) {
            errors.put("name", "Please enter name food");
        }
        if (nameFood != null && nameFood.length() > 0 && nameFood.length() < 7) {
            errors.put("nameFoodLess", "Food names must be longer than 7 characters");
        }
        if (price < 0) {
            errors.put("price", "The price of the dish must be greater than 0");
        }
    }

    public Food() {
    }

    public Food(int id, String nameFood, int categoryId, String description, String avatar, double price, LocalDateTime salesStartDate, LocalDateTime updatedAt, FoodStatus status) {
        this.id = id;
        this.nameFood = nameFood;
        this.categoryId = categoryId;
        this.description = description;
        this.avatar = avatar;
        this.price = price;
        this.salesStartDate = salesStartDate;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getSalesStartDate() {
        return salesStartDate;
    }

    public void setSalesStartDate(LocalDateTime salesStartDate) {
        this.salesStartDate = salesStartDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public FoodStatus getStatus() {
        return status;
    }

    public void setStatus(FoodStatus status) {
        this.status = status;
    }

    public static final class FoodBuilder {
        private int id;
        private String nameFood = "";
        private int categoryId;
        private String description;
        private String avatar;
        private double price;
        private LocalDateTime salesStartDate;
        private LocalDateTime updatedAt;
        private FoodStatus status;
        private HashMap<String, String> errors = new HashMap<>();

        private FoodBuilder() {
        }

        public static FoodBuilder aFood() {
            return new FoodBuilder();
        }

        public FoodBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public FoodBuilder withNameFood(String nameFood) {
            this.nameFood = nameFood;
            return this;
        }

        public FoodBuilder withCategoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public FoodBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public FoodBuilder withAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public FoodBuilder withPrice(double price) {
            this.price = price;
            return this;
        }

        public FoodBuilder withSalesStartDate(LocalDateTime salesStartDate) {
            this.salesStartDate = salesStartDate;
            return this;
        }

        public FoodBuilder withUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public FoodBuilder withStatus(FoodStatus status) {
            this.status = status;
            return this;
        }

        public FoodBuilder withErrors(HashMap<String, String> errors) {
            this.errors = errors;
            return this;
        }

        public Food build() {
            Food food = new Food();
            food.setId(id);
            food.setNameFood(nameFood);
            food.setCategoryId(categoryId);
            food.setDescription(description);
            food.setAvatar(avatar);
            food.setPrice(price);
            food.setSalesStartDate(salesStartDate);
            food.setUpdatedAt(updatedAt);
            food.setStatus(status);
            food.setErrors(errors);
            return food;
        }
    }
}
