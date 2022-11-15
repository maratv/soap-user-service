package soapuserservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soapuserservice.entity.User;
import soapuserservice.exception.UserAlreadyExistException;
import soapuserservice.exception.UserNotFoundException;
import soapuserservice.repository.UserRepository;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
//@RequiredArgsConstructor
@javax.jws.WebService(
        serviceName = "UserWebService",
        targetNamespace = "http://service.soap.user/",
        endpointInterface = "soapuserservice.service.UserWebService")
public class UserWebServiceImpl implements UserWebService {

    @Autowired
    private UserRepository userRepository;

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
        Optional<User> optUser = userRepository.findByName(name);
        if (optUser.isPresent()) {
            User user = optUser.get();
            return user;
        } else throw new UserNotFoundException(name);
    }

    @Override
    public void deleteUserByName(String name) {
        Optional<User> optUser = userRepository.findByName(name);
        if (optUser.isPresent()) {
            userRepository.delete(optUser.get());
        } else throw new UserNotFoundException(name);
    }

    @Override
    public void addUser(User user) {
        if (userRepository.findById(user.getLogin()).isPresent()) {
            userRepository.save(user);
        } else throw new UserAlreadyExistException(user.getLogin());
    }

    @Override
    public void updateUser(User user) {

    }


}


