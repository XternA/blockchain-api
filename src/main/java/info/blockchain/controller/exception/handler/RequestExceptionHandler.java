package info.blockchain.controller.exception.handler;

import info.blockchain.exception.IncorrectAddressException;
import info.blockchain.exception.RequestException;
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

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<String> requestErrorException(final RequestException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IncorrectAddressException.class)
    public ResponseEntity<String> requestErrorException(final IncorrectAddressException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
