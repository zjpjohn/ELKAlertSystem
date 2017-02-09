package com.alert.exception;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.exception
 * User: zjprevenge
 * Date: 2016/12/7
 * Time: 17:21
 */
public class UnAuthorizedException extends Exception {

    public UnAuthorizedException() {
        super();
    }

    public UnAuthorizedException(String message) {
        super(message);
    }

    public UnAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthorizedException(Throwable cause) {
        super(cause);
    }
}
