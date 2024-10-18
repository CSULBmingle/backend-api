package csulb.mingle.global.error.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {
    int getStatusCode();

    HttpStatus getHttpStatus();

    String getErrorMessage();
}
