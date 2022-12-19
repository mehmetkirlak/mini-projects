package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WrongRowException extends RuntimeException {
    private String error;

    public WrongRowException(String error) {
        this.error = error;
    }
}
