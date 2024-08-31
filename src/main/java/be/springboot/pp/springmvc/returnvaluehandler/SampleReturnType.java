package be.springboot.pp.springmvc.returnvaluehandler;

public class SampleReturnType {
    private String message;

    public SampleReturnType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
