package com.eleks.game.exception.handler;

import com.eleks.game.exception.DuplicateRoomException;
import com.eleks.game.exception.RoomNotFoundException;
import com.eleks.game.exception.RoomStateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleExceptions(Exception ex)
    {
        return new ResponseEntity<>(new ErrorResponse("Server Error",
            Collections.singletonList(ex.getLocalizedMessage())),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleRecordNotFoundException(RoomNotFoundException ex)
    {
        return new ResponseEntity<>(new ErrorResponse("Not Found",
            Collections.singletonList(ex.getLocalizedMessage())),
            HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateRoomException.class)
    public final ResponseEntity<ErrorResponse> handleDuplicateException(DuplicateRoomException ex)
    {
        return new ResponseEntity<>(new ErrorResponse("Conflict",
            Collections.singletonList(ex.getLocalizedMessage())),
            HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RoomStateException.class)
    public final ResponseEntity<ErrorResponse> handleWeightIsOverException(RoomStateException ex)
    {
        return new ResponseEntity<>(new ErrorResponse("Forbidden",
            Collections.singletonList(ex.getLocalizedMessage())),
            HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request)
    {
        return ex.getBindingResult().getAllErrors()
            .stream()
            .map(ObjectError::getDefaultMessage)
            .collect(collectingAndThen(
                toList(),
                details -> ResponseEntity.badRequest()
                    .body(new ErrorResponse("Validation failed!", details))
            ));
    }
}
