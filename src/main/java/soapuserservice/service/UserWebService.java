package soapuserservice.service;

import soapuserservice.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.concurrent.CompletableFuture;

@WebService
public interface UserWebService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(
            localName = "sayHello") //,
          //  targetNamespace = "http://service.soap.user/")
    @WebMethod(action = "urn:SayHello")
    @ResponseWrapper(
            localName = "sayHelloResponse") //,
         //   targetNamespace = "http://service.soap.user/")
    String sayHello(@WebParam(name = "name") String name);

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(
            localName = "addUser",
            targetNamespace = "http://service.soap.user/")
    @WebMethod
    @ResponseWrapper(
            localName = "addUserResponse",
            targetNamespace = "http://service.soap.user/")
  //  void addUser(@WebParam(name = "user", targetNamespace = "") User user);
    CompletableFuture<Void> addUser(@WebParam(name = "user") User user);


    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(
            localName = "getAllUsers",
            targetNamespace = "http://service.soap.user/")
    @WebMethod
    @ResponseWrapper(
            localName = "getAllUsersResponse",
            targetNamespace = "http://service.soap.user/")





    CompletableFuture<Void> editUser(User user);

    CompletableFuture<Void> deleteUserByUsername(String username);

    //  CompletableFuture<Iterable<User>> getAllUsers();
    CompletableFuture<Void> deleteRoleByUsername(String username, Long id);

    CompletableFuture<Void> addRole(String username, long role);

    CompletableFuture<User> getUserByUsernameService(String username);


}


