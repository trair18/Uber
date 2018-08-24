package com.gmail.trair8.exception;

public class CarException extends Exception{

    public CarException(Throwable cause) {
        super(cause);
    }

    public CarException(String message, Throwable cause) {
        super(message, cause);
    }
}
