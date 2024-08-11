package homework_5.controller;

import homework_5.dto.ErrorResponseDto;
import homework_5.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleUserNotFoundException(CustomException exception) {
        return new ErrorResponseDto(exception.getMessage(), exception.getHttpStatus().value());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleMethodArgumentTypeMismatchException() {
        return new ErrorResponseDto("Неверный тип параметра!", HttpStatus.BAD_REQUEST.value());
    }
}
