package pro.sky.skyproMockhw2_12.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeAlreadyAddedException extends RuntimeException {

    public EmployeeAlreadyAddedException(String message) {

        super(message);
    }
}
