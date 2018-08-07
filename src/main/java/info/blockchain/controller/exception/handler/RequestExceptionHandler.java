package info.blockchain.controller.exception.handler;

import info.blockchain.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(
        assignableTypes = RestController.class,
        annotations = RestController.class
)
@SuppressWarnings("unused")
public final class RequestExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> requestErrorException(final CustomException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
