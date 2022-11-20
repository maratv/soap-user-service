package soapuserservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soapuserservice.entity.Role;
import soapuserservice.entity.User;
import soapuserservice.exception.UserNotFoundException;
import soapuserservice.repository.RoleRepository;
import soapuserservice.repository.UserRepository;
import soapuserservice.validator.UserValidator;

import javax.jws.WebService;
import java.util.HashSet;
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
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<String> getAllUsers() {
        return userRepository.findAll().stream()
                .map(User::toString)
                .collect(Collectors.toList());
    }

    @Override
    public User getUserByName(String name) {
        Optional<User> optionalUser = userRepository.findByName(name);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException(name);
        }
    }

    @Override
    public ValidationResponse deleteUserByName(String name) {
        Optional<User> optionalUser = userRepository.findByName(name);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return new ValidationResponse();
        } else {
            return new ValidationResponse(List.of("User with name '" + name + "' not found"));
        }
    }

    @Override
    public ValidationResponse addUser(User user) {
        if (checkValid(user).size() == 0) {
            if (userRepository.findById(user.getLogin()).isEmpty()) {
                if (roleChecker(user)) {
                    userRepository.save(user);
                } else {
                    return new ValidationResponse(List.of("Incorrect roles input, roles not found"));
                }
                return new ValidationResponse();
            } else
                return new ValidationResponse(List.of("User with login: '" + user.getLogin() + "' already exist"));
        } else return new ValidationResponse(checkValid(user));
    }

    @Override
    public ValidationResponse updateUser(User user) {
        if (userRepository.existsById(user.getLogin())) {
            if (checkValid(user).size() == 0) {
                User editedUser = userRepository.findById(user.getLogin()).get();
                editedUser.setName(user.getName());
                editedUser.setPassword(user.getPassword());
                if (roleChecker(user)) {
                    editedUser.setRoles(user.getRoles());
                } else {
                    return new ValidationResponse(List.of("Incorrect roles input, roles not found"));
                }
                userRepository.save(editedUser);
                return new ValidationResponse();
            } else {
                return new ValidationResponse(checkValid(user));
            }
        } else {
            return new ValidationResponse(List.of("User '" + user.getLogin() + "' not found"));
        }
    }

    private List<String> checkValid(User user) {
        UserValidator userValidator = new UserValidator();
        return userValidator.validation(user);
    }

    private Boolean roleChecker(User user) {
        return new HashSet<>(new HashSet<>(roleRepository.findAll()).stream()
                .map(Role::getId)
                .collect(Collectors.toList()))
        .containsAll(user.getRoles().stream()
                .map(Role::getId)
                .collect(Collectors.toList()));
    }
}