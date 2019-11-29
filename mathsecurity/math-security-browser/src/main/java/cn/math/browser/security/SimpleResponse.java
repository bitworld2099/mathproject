package cn.math.browser.security;

public class SimpleResponse {
    private Object message;

    public SimpleResponse(Object message) {
        this.message = message;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
