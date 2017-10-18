package com.github.sejoung.api.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author kim se joung
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<String> handleError(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>("{}", httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
