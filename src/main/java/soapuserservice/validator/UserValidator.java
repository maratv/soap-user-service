package soapuserservice.validator;

import org.springframework.stereotype.Component;
import soapuserservice.entity.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    public List<String> validation(User user) {
        List<String> errors = new ArrayList<>();
        if (user.getLogin().equals("")) {
            errors.add("Empty login");
        }
        if (user.getName().equals("")) {
            errors.add("Empty name");
        }
        if (user.getPassword().equals("")) {
            errors.add("Empty password");
        } else {
            if (!user.getPassword().matches("(.)*\\d(.)*")) {
                errors.add("Password doesn't contain number");
            }
            if (!user.getPassword().matches("(.)*[A-Z](.)*")) {
                errors.add("Password doesn't contain a capital letter");
            }
        }
        return errors;
    }
}
