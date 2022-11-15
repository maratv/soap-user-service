package soapuserservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soapuserservice.entity.User;
import soapuserservice.exception.UserAlreadyExistException;
import soapuserservice.exception.UserNotFoundException;
import soapuserservice.exception.UserValidationException;
import soapuserservice.repository.UserRepository;
import soapuserservice.validator.Validator;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@WebService(
        serviceName = "UserWebService",
        targetNamespace = "http://service.soap.user/",
        endpointInterface = "soapuserservice.service.UserWebService")
public class UserWebServiceImpl implements UserWebService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;


    @Override
    public String sayHello(String name) {

//        User myUser = new User("weq", "ewq", "ewqe");
//        userList.add(myUser);
//        userList.forEach(System.out::println);
        userRepository.findAll().forEach(System.out::println);

        return "Привет" + name;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
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
        } else return new ValidationResponse("Don't found user with name: " +  name);
    }

//    @Override
//    public void addUser(User user) {
//        List<String> errors = validator.validation(user);
//        if (errors.size() == 0) {
//            if (!userRepository.findById(user.getLogin()).isPresent()) {
//                userRepository.save(user);
//            } else throw new UserAlreadyExistException(user.getLogin());
//        } else throw new UserValidationException(errors);
//    }

    @Override
    public ValidationResponse addUser(User user) {
        List<String> errors = validator.validation(user);
        if (errors.size() == 0) {
            if (!userRepository.findById(user.getLogin()).isPresent()) {
                userRepository.save(user);
                return new ValidationResponse();
            } else throw new UserAlreadyExistException(user.getLogin());
            //} else throw new UserValidationException(errors);
        } else return new ValidationResponse(errors);
    }


    @Override
    public void updateUser(User user) {
        List<String> errors = validator.validation(user);
        if (errors.size() == 0) {
            if (!userRepository.findById(user.getLogin()).isPresent()) {
                userRepository.save(user);
            } else throw new UserAlreadyExistException(user.getLogin());
        } else throw new UserValidationException(errors);


    }


}