package site.metacoding.addressapi.handler.ex;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
