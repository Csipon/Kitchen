package com.csipon.kitchen.ad;

/*
* throw exception where no available video
*/

public class NoVideoAvailableException extends Exception {

    public NoVideoAvailableException() {
    }

    public NoVideoAvailableException(String message) {
        super(message);
    }

    public NoVideoAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoVideoAvailableException(Throwable cause) {
        super(cause);
    }

    public NoVideoAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
