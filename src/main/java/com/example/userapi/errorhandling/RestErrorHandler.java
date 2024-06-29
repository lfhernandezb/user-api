package com.example.userapi.errorhandling;

import com.example.userapi.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestErrorHandler { //extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestErrorHandler.class);

    //@Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex/*, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request*/) {
        Map<String, List<String>> body = new HashMap<>();

        logger.info("ex: " + ex.toString());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    /*
    //@Override
    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<Object> handleExpiredJwtException(
            ExpiredJwtException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        logger.info("ExpiredJwtException thrown");
        //return buildResponseEntity(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex));
        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

     */

    @ExceptionHandler(RestClientException.class)
    protected ResponseEntity<Object> handleRestClientException(
            RestClientException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        logger.info("RestClientException thrown");

        //return buildResponseEntity(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex));
        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // custom errors
    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<Object> handleAccountNotFoundException(
            EmailAlreadyRegisteredException ex, WebRequest request) {

        logger.info("EmailAlreadyRegisteredException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailNotProvidedException.class)
    public ResponseEntity<Object> handleEmailNotProvidedException (
            EmailNotProvidedException ex, WebRequest request) {

        logger.info("EmailNotProvidedException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Object> handleClientNotFoundException(
            InvalidEmailException ex, WebRequest request) {

        logger.info("InvalidEmailException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Object> handleCInvalidPasswordException(
            InvalidPasswordException ex, WebRequest request) {

        logger.info("InvalidPasswordException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameNotProvidedException.class)
    public ResponseEntity<Object> handleCityNotFoundException(
            NameNotProvidedException ex, WebRequest request) {

        logger.info("InvalidPasswordException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotProvidedException.class)
    public ResponseEntity<Object> handlePasswordNotProvidedException(
            PasswordNotProvidedException ex, WebRequest request) {

        logger.info("PasswordNotProvidedException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PhoneNotProvidedException.class)
    public ResponseEntity<Object> handlePhoneNotProvidedException(
            PhoneNotProvidedException ex, WebRequest request) {

        logger.info("PhoneNotProvidedException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingConfigurationValueException.class)
    public ResponseEntity<Object> handleMissingConfigurationValueException(
            MissingConfigurationValueException ex, WebRequest request) {

        logger.info("MissingConfigurationValueException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleMovementTypeNotFoundException(
            UserNotFoundException ex, WebRequest request) {

        logger.info("UserNotFoundException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException (
            ExpiredJwtException ex, WebRequest request) {

        logger.info("ExpiredJwtException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", "JWT expirado"); //ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<Object> handleHttpClientErrorException(
            ExecutionException ex, WebRequest request) {

        logger.info("ExecutionException thrown " + ex);

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(
            Exception ex, WebRequest request) {

        logger.info("Exception thrown " + ex);

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());

        if (ex.getMessage().contains("HttpClientErrorException$NotFound")) {
            body.put("error", "Entidad no encontrada");
        }
        else {
            body.put("error", ex.getMessage());
        }

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


}
