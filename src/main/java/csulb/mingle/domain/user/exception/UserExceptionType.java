package csulb.mingle.domain.user.exception;

import csulb.mingle.global.error.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum UserExceptionType implements BaseExceptionType {

    USER_ALREADY_EXIST(409, HttpStatus.CONFLICT, "User account that already exists."),
    NEED_VERIFIED_EMAIL(401, HttpStatus.UNAUTHORIZED, "E-mail authentication required.");

    private final int statusCode;
    private final HttpStatus httpStatus;
    private final String errorMessage;

    UserExceptionType(int statusCode, HttpStatus httpStatus, String errorMessage) {
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
