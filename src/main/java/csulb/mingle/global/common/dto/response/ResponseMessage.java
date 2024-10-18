package csulb.mingle.global.common.dto.response;

import lombok.Getter;

@Getter
public class ResponseMessage {
    private Object data;
    private String errorMessage;
    private Integer statusCode;

    public ResponseMessage(Object data, Integer statusCode) {
        this.data = data;
        this.statusCode = statusCode;
    }

    public ResponseMessage(String errorMessage, Integer statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public ResponseMessage(Object data, String errorMessage, Integer statusCode) {
        this.data = data;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }
}
