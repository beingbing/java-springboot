package be.springboot.pp.springmvc.exceptions;

public class CustomException extends Exception {
    private final String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }
}
