package sdkdata.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NULL_DATA(0, "Data does not exist"),
    WRONG_DATA_TYPE(1, "Data type is wrong"),
    PROJECT_NOT_FOUND(2, "No project at all"),
    PROJECT_NOT_CREATED(3, "Failed to create project");

    private final int code;
    private final String description;

    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
