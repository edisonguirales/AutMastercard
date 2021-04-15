package com.mastercard.lts.rewards.pwrautomation.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String PWR_MIDDLEWARE_CLIENT = "PWRMiddleTierClient";

    @ExceptionHandler({InvalidRequest.class})
    protected ResponseEntity<Object> handleInvalidRequest(InvalidRequest ex) {
        String[] errorMessage;
        String description;
        String details;
        if (ex != null && ex.getLocalizedMessage()!=null && (ex.getLocalizedMessage().contains("Errors") || ex.getLocalizedMessage().contains("errors"))) {
            errorMessage = ex.getLocalizedMessage().split(",");
            List<String> errorString = new ArrayList<>();
            for (String s : errorMessage) {
                String[] items = s.split(":");
                Collections.addAll(errorString, items);
            }
            description = errorString.get(7).replaceAll("[\"\\s+]", "");
            details = errorString.get(11).replaceAll("[]}{\"\n+]", "");
        } else {
            description = "Invalid Request";
            assert ex != null;
            details = ex.getLocalizedMessage();
        }
        List<ErrorItem> errorItemsList = getErrorItemList(
                ex.getCode(),
                description,
                details);
        ErrorsList errorsList = getErrorsList(errorItemsList);
        log.error(errorItemsList.get(0).getDetails());
        return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);
    }

    private List<ErrorItem> getErrorItemList(String reasonCode, String description, String details) {
        List<ErrorItem> errors = new ArrayList<>();
        ErrorItem error = new ErrorItem();
        error.setSource(APIExceptionHandler.PWR_MIDDLEWARE_CLIENT);
        error.setDescription(description);
        error.setReasonCode(reasonCode);
        error.setRecoverable(false);
        error.setDetails(details);
        errors.add(error);
        return errors;
    }

    private ErrorsList getErrorsList(List<ErrorItem> errorItemList) {
        ErrorsList errorsList = new ErrorsList();
        Errors errors = new Errors();
        errors.setError(errorItemList);
        errorsList.setErrors(errors);
        return errorsList;
    }
}
