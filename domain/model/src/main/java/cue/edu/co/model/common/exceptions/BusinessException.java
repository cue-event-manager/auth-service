package cue.edu.co.model.common.exceptions;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final String message;
    private final String code;

    public BusinessException(String message, String code){
        super(message);
        this.message = message;
        this.code = code;
    }
}
