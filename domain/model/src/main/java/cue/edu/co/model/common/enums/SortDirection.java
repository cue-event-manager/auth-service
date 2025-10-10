package cue.edu.co.model.common.enums;

public enum SortDirection {
    ASC,
    DESC;

    public static SortDirection from(String value) {
        if (value == null) return ASC;
        return value.trim().equalsIgnoreCase("desc") ? DESC : ASC;
    }
}
