package com.mastercard.lts.rewards.pwrautomation.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRequest extends RuntimeException{

    private List<ErrorItem> errors;
    private String code;


    public InvalidRequest() {
        super();
    }
    public InvalidRequest(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidRequest(String message) {
        super(message);
    }
    public InvalidRequest(HttpStatus httpStatus) {
       super(httpStatus.toString());
    }
    public InvalidRequest(Throwable cause) {
        super(cause);
    }
    public InvalidRequest(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public InvalidRequest(List<ErrorItem> errors){
        super();
        getErrors().addAll(errors);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ErrorItem> getErrors() {
        if(errors==null){
            errors = new ArrayList<>();
        }
        return errors;
    }
}
