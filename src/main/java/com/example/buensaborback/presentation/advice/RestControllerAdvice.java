package com.example.buensaborback.presentation.advice;
import com.example.buensaborback.domain.dtos.error.ErrorDto;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class RestControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(RestControllerAdvice.class);

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException e){
        String errorMsg = e.getClass().getSimpleName()+ " : " + e.getMessage();
        logger.error(errorMsg);
        return new ResponseEntity<>(ErrorDto.builder()
                .errorMsg(e.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }
}
