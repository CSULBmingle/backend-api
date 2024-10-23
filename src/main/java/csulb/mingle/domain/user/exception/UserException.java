package csulb.mingle.domain.user.exception;

import csulb.mingle.global.error.exception.BaseException;
import csulb.mingle.global.error.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserException extends BaseException {

    private final BaseExceptionType exceptionType;

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
