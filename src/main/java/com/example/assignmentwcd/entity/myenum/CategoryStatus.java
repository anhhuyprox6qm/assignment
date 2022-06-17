package com.example.assignmentwcd.entity.myenum;

public enum CategoryStatus {
    ACTIVE(1), DEACTIVE(0), DELETED(-1), UNDEFINED(-2);

    private int value;

    CategoryStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CategoryStatus of(int value) {
        for (CategoryStatus accountStatus :
                CategoryStatus.values()) {
            if (accountStatus.getValue() == value) {
                return accountStatus;
            }
        }
        return CategoryStatus.UNDEFINED;
    }
}
