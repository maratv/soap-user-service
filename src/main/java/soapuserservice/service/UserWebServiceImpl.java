package soapuserservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soapuserservice.entity.User;
import soapuserservice.exception.UserAlreadyExistException;
import soapuserservice.exception.UserNotFoundException;
import soapuserservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@javax.jws.WebService(
        serviceName = "UserWebService",
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

    @Override
    public String sayHello(String name) {

        User myUser = new User("test", "testPassword");
        userList.add(myUser);
        userList.forEach(System.out::println);
        userRepository.findAll().forEach(System.out::println);

        return "Привет" + name;
    }

//    @Override
//    public CompletableFuture<Iterable<User>> getAllUsers() {
//        return CompletableFuture.supplyAsync(userRepository::findAll);
//    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    @Override
    public CompletableFuture<User> getUserByName(String name) {
        return CompletableFuture.supplyAsync(() -> getUserByNameService(name));
    }

    @Override
    public CompletableFuture<Void> deleteUserByName(String name) {
        return CompletableFuture.runAsync(() -> {
            if(userRepository.findById(name).isPresent()) {
                userRepository.deleteById(name);
            }
            else throw new UserNotFoundException(name);
        }, service);
    }

    @Override
    public CompletableFuture<Void> addUser(User user) {
        return CompletableFuture.runAsync(() -> {
            if(!userRepository.existsById(user.getName())){
                userRepository.save(user);
            }
            else throw new UserAlreadyExistException(user.getName());
        }, service);
    }

    @Override
    public CompletableFuture<Void> updateUser(User user) {
        return null;
    }


    private User getUserByNameService(String name) {
        Optional<User> opt = userRepository.findById(name);
        if(opt.isPresent()) {
            User user = opt.get();
//            Iterable<Role> roleSet = roleRepository.findAllById(user.getRoleRefSet()
//                    .stream().map(RoleRef::getId)
//                    .collect(Collectors.toSet()));
//            roleSet.forEach((role) -> user.getRoleSet().add(role));
//            log.info("Get user: {}", user);
            return user;
        } else throw new UserNotFoundException(name);
    }

}


