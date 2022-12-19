package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class OccupiedSeatException extends RuntimeException {
    private String error;
    public OccupiedSeatException(String error) {
        this.error = error;
    }
}
