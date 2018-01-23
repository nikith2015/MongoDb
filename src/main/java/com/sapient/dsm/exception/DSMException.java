package com.sapient.dsm.exception;

public class DSMException extends Throwable {

    private final int code;
    public DSMException() {
        this(500);
    }
    public DSMException(int code) {
        this(code, "Error while processing the request", null);
    }
    public DSMException(int code, String message) {
        this(code, message, null);
    }
    public DSMException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
