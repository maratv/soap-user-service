package soapuserservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soapuserservice.entity.User;
import soapuserservice.exception.UserNotFoundException;
import soapuserservice.repository.UserRepository;
import soapuserservice.validator.UserValidator;

import javax.jws.WebService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@WebService(
        serviceName = "UserWebService",
        targetNamespace = "http://service.soap.user/",
        endpointInterface = "soapuserservice.service.UserWebService")
public class UserWebServiceImpl implements UserWebService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<String> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> user.toString())
                .collect(Collectors.toList());
    }

    @Override
    public User getUserByName(String name) {
        Optional<User> optionalUser = userRepository.findByName(name);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else throw new UserNotFoundException(name);
    }

    @Override
    public ValidationResponse deleteUserByName(String name) {
        Optional<User> optionalUser = userRepository.findByName(name);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return new ValidationResponse();
        } else return new ValidationResponse(List.of("User with name '" + name + "' not found"));
    }

    @Override
    public ValidationResponse addUser(User user) {
        if (createValidator(user).size() == 0) {
            if (userRepository.findById(user.getLogin()).isEmpty()) {
                userRepository.save(user);
                return new ValidationResponse();
            } else
                return new ValidationResponse(List.of("User with login: '" + user.getLogin() + "' already exist"));
        } else return new ValidationResponse(createValidator(user));
    }

    @Override
    public ValidationResponse updateUser(User user) {
        if (userRepository.existsById(user.getLogin())) {
            if (createValidator(user).size() == 0) {
                userRepository.deleteById(user.getLogin());
                userRepository.flush();
                userRepository.save(user);
                return new ValidationResponse();
            } else return new ValidationResponse(createValidator(user));
        } else return new ValidationResponse(List.of("User '" + user.getLogin() + "' not found"));
    }

    private List<String> createValidator(User user) {
        UserValidator userValidator = new UserValidator();
        List<String> errors = userValidator.validation(user);
        return errors;
    }
}