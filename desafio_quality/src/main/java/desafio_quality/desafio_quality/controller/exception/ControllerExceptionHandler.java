package desafio_quality.desafio_quality.controller.exception;

import desafio_quality.desafio_quality.dto.response.ErrorDTO;
import desafio_quality.desafio_quality.exception.PropertyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> methodNotValidException(MethodArgumentNotValidException err) {
        List<ObjectError> allErrors = err.getBindingResult().getAllErrors();
        List<ErrorDTO> errorDTOS = new ArrayList<>();
        allErrors.forEach(
                objectError -> errorDTOS.add(
                        new ErrorDTO("MethodArgumentNotValidException", objectError.getDefaultMessage()))
        );
        return new ResponseEntity<>(errorDTOS, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyNotFoundException.class)
    protected ResponseEntity<?> propertyNotFoundException(PropertyNotFoundException err) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("PropertyNotFoundException", err.getMessage()));
    }
}
