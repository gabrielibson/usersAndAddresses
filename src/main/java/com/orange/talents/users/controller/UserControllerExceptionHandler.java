package com.orange.talents.users.controller;

import com.orange.talents.users.dto.ErrorResponseDTO;
import com.orange.talents.users.exceptions.ElementNotFoundException;
import com.orange.talents.users.exceptions.IntegrityViolationException;
import com.orange.talents.users.exceptions.InvalidDateFormatException;
import com.orange.talents.users.exceptions.ViaCEPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class UserControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(UserControllerExceptionHandler.class);

    @ExceptionHandler(ElementNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDTO handleElementNotFoundException(ElementNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDTO handleIntegrityViolationException(IntegrityViolationException ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDateFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDTO handleIntegrityViolationException(InvalidDateFormatException ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ViaCEPException.class)
    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ErrorResponseDTO handleViaCEPException(ViaCEPException ex) {
        return buildErrorResponse(ex, HttpStatus.EXPECTATION_FAILED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(validationList, status);
    }

    private ErrorResponseDTO buildErrorResponse(Exception e, HttpStatus status) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(status.value())
                .message(e.getMessage())
                .build();
        logger.error(errorResponseDTO.getMessage(), errorResponseDTO);
        return errorResponseDTO;
    }
}
