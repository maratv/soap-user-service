package soapuserservice.exception;

public class UserNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    
    public UserNotFoundException(String message) {
        super("User with name: '" + message + "' not found");
    }
}
