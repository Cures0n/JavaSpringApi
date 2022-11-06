package klimov.test.spring.controller;

import klimov.test.spring.models.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptiomResponse extends Throwable {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<JsonResponse> handleException(Exception e) {
        return new ResponseEntity<JsonResponse>(new JsonResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}