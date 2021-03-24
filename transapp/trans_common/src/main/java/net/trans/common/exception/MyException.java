package net.trans.common.exception;

/**
 * @Author Cx
 * @Date 2021-02-05
 * @Version 1.0
 */
public class MyException extends RuntimeException {

    public MyException(String msg) {
        super(msg);
    }

    public MyException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public MyException(Throwable throwable) {
        super(throwable);
    }
}
