package app.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LessonType {
    LECTURE("LECTURE"),
    PRACTICAL("PRACTICAL"),
    LABORATORY("LABORATORY");

    private String value;

    LessonType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static LessonType fromValue(String value) {
        for (LessonType type : LessonType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid LessonType value: " + value);
    }
}
