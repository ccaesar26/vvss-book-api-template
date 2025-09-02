package ro.unitbv.fmi.vvss.bookapi.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.CONFLICT)
public class IsbnAlreadyExistsException extends RuntimeException {
    public IsbnAlreadyExistsException(String message) {
        super(message);
    }
}
