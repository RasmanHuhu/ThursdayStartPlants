package dat.ErrorHandling;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    public int statusCode;

    public ApiException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}