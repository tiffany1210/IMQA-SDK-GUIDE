package sdkdata.exception;


public class DataFormatException extends RuntimeException {
    private ErrorCode code;

    public ErrorCode getCode() {
        return this.code;
    }
    public DataFormatException(String msg, ErrorCode code) {
        super(msg);
        this.code = code;
    }
}
