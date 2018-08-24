package com.gmail.trair8.exception;

public class RepositoryException extends Exception {
    public RepositoryException(Throwable cause) {
        super(cause);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
