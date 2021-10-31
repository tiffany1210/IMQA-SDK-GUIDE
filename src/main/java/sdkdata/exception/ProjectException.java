package sdkdata.exception;

public class ProjectException extends RuntimeException {
    private ErrorCode code;

    public ErrorCode getCode() {
        return this.code;
    }
    public ProjectException(ErrorCode code) {
        super();
        this.code = code;
    }
}
