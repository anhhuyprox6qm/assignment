package com.example.assignmentwcd.entity.myenum;

public class FoodStatus {
    ARE_SELLING(1), STOP_SELLING(0), DELETED(-1), UNDEFINED(-2);

    private int value;

    FoodStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FoodStatus of(int value) {
        for (FoodStatus foodStatus :
                FoodStatus.values()) {
            if (foodStatus.getValue() == value) {
                return foodStatus;
            }
        }
        return FoodStatus.UNDEFINED;
    }
}
