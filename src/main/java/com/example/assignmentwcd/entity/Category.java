package com.example.assignmentwcd.entity;

import com.example.assignmentwcd.entity.myenum.CategoryStatus;

import java.util.HashMap;

public class Category {
    private int id;
    private String name;
    private CategoryStatus status;
    HashMap<String, String> errors = new HashMap<>();
    public boolean isValid() {
        checkValid();
        return errors.size() == 0;
    }

    private void checkValid() {
        if (name == null || name.length() == 0) {
            errors.put("name", "Please enter category name.");
        }
    }
    public Category() {
    }

    public Category(int id, String name, CategoryStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }

    public static final class CategoryBuilder {
        private int id;
        private String name;
        private CategoryStatus status;

        private CategoryBuilder() {
        }

        public static CategoryBuilder aCategory() {
            return new CategoryBuilder();
        }

        public CategoryBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CategoryBuilder withStatus(CategoryStatus status) {
            this.status = status;
            return this;
        }

        public Category build() {
            Category category = new Category();
            category.setId(id);
            category.setName(name);
            category.setStatus(status);
            return category;
        }
    }
}
