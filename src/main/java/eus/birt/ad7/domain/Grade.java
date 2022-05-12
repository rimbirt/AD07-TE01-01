package eus.birt.ad7.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum Grade {
    FOUR( "IV"),
    FOUR_PLUS( "IV+"),
    FIVE( "V"),
    FIVE_PLUS( "V+"),
    SIX_A( "6a"),
    SIX_A_PLUS( "6a+"),
    SIX_B( "6b"),
    SIX_B_PLUS( "6b+"),
    SIX_C( "6c"),
    SIX_C_PLUS("6c+"),
    SEVEN_A("7a"),
    SEVEN_A_PLUS("7a+"),
    SEVEN_B("7b"),
    SEVEN_B_PLUS("7b+"),
    SEVEN_C("7c"),
    SEVEN_C_PLUS("7c+"),
    EIGHT_A("8a"),
    EIGHT_A_PLUS("8a+"),
    EIGHT_B("8b"),
    EIGHT_B_PLUS("8b+"),
    EIGHT_C("8c"),
    EIGHT_C_PLUS("8c+"),
    NINE_A("9a"),
    NINE_A_PLUS("9a+"),
    NINE_B("9b");

    private final String name;
    private static final Map<Integer, Grade> gradeMap = new HashMap<>();

    static {
        for (Grade grade : Grade.values()) {
            gradeMap.put(grade.ordinal(), grade);
        }
    }

    Grade(String name) {
        this.name = name;
    }

    public static Grade fromValue(int value) {
        return gradeMap.get(value);
    }

    @JsonValue
    @Override
    public String toString() {
        return name;
    }
}
