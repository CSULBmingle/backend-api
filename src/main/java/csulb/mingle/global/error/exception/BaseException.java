package csulb.mingle.global.error.exception;

public abstract class BaseException extends RuntimeException {

    public abstract BaseExceptionType getExceptionType();
}

