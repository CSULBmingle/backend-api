package csulb.mingle.global.error;

import csulb.mingle.global.error.exception.BaseException;
import csulb.mingle.global.common.dto.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * [Exception] RuntimeException 하위 모든 예외 발생 경우
     *
     * @param e BaseException
     * @return ResponseEntity<Message>
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ResponseMessage> handelBaseException(BaseException e) {
        ResponseMessage responseMessage = new ResponseMessage(
                e.getExceptionType().getErrorMessage(),
                e.getExceptionType().getStatusCode()
        );
        return new ResponseEntity<>(
                responseMessage,
                e.getExceptionType().getHttpStatus()
        );
    }

    /**
     * [Exception] @Valid에서 MethodArgumentNotValidException 예외 발생한 경우
     *
     * @param exception MissingServletRequestParameterException
     * @return ResponseEntity<Message>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage> handleValidationExceptions(MethodArgumentNotValidException exception) {

        // @Valid에서 넘어온 에러메세지 추출
        String errorMessage = exception.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        // 에러메세지를 담은 응답 메세지 형성
        ResponseMessage responseMessage = new ResponseMessage(errorMessage, 400);
        return new ResponseEntity<>(
                responseMessage,
                HttpStatus.BAD_REQUEST
        );
    }
}
