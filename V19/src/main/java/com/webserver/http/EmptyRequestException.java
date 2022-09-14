package com.webserver.http;

/**
 * 空请求异常
 * 当HttpServletRequest在解析请求时发现本次为空请求时会抛出这个异常
 * 自定义异常
 * 1.类名见名知意
 * 2.需要继承Exception
 * 3.提供超累异常提供的所有构造器
 */
public class EmptyRequestException extends Exception{
    public EmptyRequestException() {
    }

    public EmptyRequestException(String message) {
        super(message);
    }

    public EmptyRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyRequestException(Throwable cause) {
        super(cause);
    }

    public EmptyRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
