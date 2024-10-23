package csulb.mingle.domain.auth.exception;

import csulb.mingle.global.error.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum AuthExceptionType implements BaseExceptionType {

    EMAIL_SEND_FAILED(500, HttpStatus.INTERNAL_SERVER_ERROR, "failed to send email.");

    private final int statusCode;
    private final HttpStatus httpStatus;
    private final String errorMessage;

    AuthExceptionType(int statusCode, HttpStatus httpStatus, String errorMessage) {
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
