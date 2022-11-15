package soapuserservice.exception;

import java.util.List;

public class UserValidationException extends RuntimeException {

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public UserValidationException(List<String> messages) {
        super("Verification failed for this reason:" + messages);
    }
}
