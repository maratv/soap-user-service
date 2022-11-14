/*
package soapuserservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soapuserservice.entity.User;
import soapuserservice.exception.UserAlreadyExistException;
import soapuserservice.repository.UserRepository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

//@RequiredArgsConstructor

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
 //   private final ExecutorService service;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public CompletableFuture<Void> addUser(User user) {
//        return CompletableFuture.runAsync(() -> {
//            if (!userRepository.existsById(user.getName())) {
//                // User savedUser =
//                userRepository.save(user);
//                //  log.info("Saved user: {}", savedUser);
//            }
//              else throw new UserAlreadyExistException(user.getName());
//              }, service);


               return null;
        }

        @Override
        public CompletableFuture<Void> editUser (User user){
            return null;
        }

        @Override
        public CompletableFuture<Void> deleteUserByUsername (String username){
            return null;
        }

        @Override
        public CompletableFuture<Iterable<User>> getAllUsers () {
            return null;
        }

        @Override
        public CompletableFuture<Void> deleteRoleByUsername (String username, Long id){
            return null;
        }

        @Override
        public CompletableFuture<Void> addRole (String username,long role){
            return null;
        }

        @Override
        public CompletableFuture<User> getUserByUsernameService (String username){
            return null;
        }
    }



 */
