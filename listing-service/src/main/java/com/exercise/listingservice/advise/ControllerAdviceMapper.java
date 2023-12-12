package com.exercise.listingservice.advise;

import com.exercise.listingservice.dto.ErrorDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ControllerAdviceMapper {

    @ExceptionHandler({ConstraintViolationException.class, HttpClientErrorException.class,
            MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDto> handleClientException(Exception e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        errorDto.setResult(false);

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
