package com.kulygina.model.enums;

public enum DenominationEnum {
    DENOMINATION_5(5),
    DENOMINATION_10(10),
    DENOMINATION_20(20),
    DENOMINATION_50(50),
    DENOMINATION_100(100),
    DENOMINATION_500(500);

    private int value;

    DenominationEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DenominationEnum fromValue(int value) {
        for (DenominationEnum denomination : values()) {
            if (denomination.getValue() == value) {
                return denomination;
            }
        }
        return null;
    }
}
