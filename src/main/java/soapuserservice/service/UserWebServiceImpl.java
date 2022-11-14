package soapuserservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soapuserservice.entity.User;
import soapuserservice.exception.UserAlreadyExistException;
import soapuserservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@NoArgsConstructor
@Service
@RequiredArgsConstructor
@javax.jws.WebService(
        serviceName = "UserWebService",
//        portName = "SoapUserServicePort",
        targetNamespace = "http://service.soap.user/",
        endpointInterface = "soapuserservice.service.UserWebService")
public class UserWebServiceImpl implements UserWebService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    public UserWebServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    private final ExecutorService service = Executors.newFixedThreadPool(5);;
    List<User> userList = new ArrayList<>();


    void myAddUser(User user) {
        userRepository.save(user);
    }


    @Override
    public String sayHello(String name) {

        User myUser = new User("test", "testPassword");
        userList.add(myUser);
        userList.forEach(System.out::println);
        //   myAddUser(myUser);
        userRepository.findAll().forEach(System.out::println);

        return "Привет" + name;
    }

    @Override
    public CompletableFuture<Void> addUser(User user) {
        return CompletableFuture.runAsync(() -> {
            if(!userRepository.existsById(user.getName())){
                User savedUser = userRepository.save(user);
               // log.info("Saved user: {}", savedUser);
            }
            else throw new UserAlreadyExistException(user.getName());
        }, service);
    }




//    @Override
//    public List<User> getAllUsers() {
//        return null;
//    }

//    @Override
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }


    @Override
    public CompletableFuture<Void> editUser(User user) {
        return null;
    }

    @Override
    public CompletableFuture<Void> deleteUserByUsername(String username) {
        return null;
    }

//    @Override
//    public CompletableFuture<Iterable<User>> getAllUsers() {
//        return null;
//    }

    @Override
    public CompletableFuture<Void> deleteRoleByUsername(String username, Long id) {
        return null;
    }

    @Override
    public CompletableFuture<Void> addRole(String username, long role) {
        return null;
    }

    @Override
    public CompletableFuture<User> getUserByUsernameService(String username) {
        return null;
    }


}


