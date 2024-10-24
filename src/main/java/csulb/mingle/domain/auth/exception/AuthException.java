package csulb.mingle.domain.auth.exception;

import csulb.mingle.global.error.exception.BaseException;
import csulb.mingle.global.error.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthException extends BaseException {

    private final BaseExceptionType exceptionType;

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
