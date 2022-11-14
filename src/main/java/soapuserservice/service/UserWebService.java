package soapuserservice.service;

import soapuserservice.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@WebService
public interface UserWebService {


    @WebResult(name = "return")
    @RequestWrapper(localName = "sayHello") //,
    @WebMethod(action = "urn:SayHello")
    @ResponseWrapper(localName = "sayHelloResponse")
    String sayHello(@WebParam(name = "name") String name);                     // тестовый

    @WebResult(name = "return")
    @RequestWrapper(localName = "getAllUsers")
    @WebMethod(action = "urn:getAllUsers")
    @ResponseWrapper(localName = "getAllUsersResponse")
        //   CompletableFuture<Iterable<User>> getAllUsers();                       // 1 Получать список без ролей
    List<User> getAllUsers();                       // 1 Получать список без ролей


    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getUserByName")
    @WebMethod
    @ResponseWrapper(localName = "getUserByNameResponse")
    CompletableFuture<User> getUserByName(@WebParam(name = "name") String name);  // 2 Получать конкретного пользователя по имени

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "deleteUserByName")
    @WebMethod
    @ResponseWrapper(localName = "deleteUserByNameResponse")
    CompletableFuture<Void> deleteUserByName(@WebParam(name = "name") String name);              // 3 Удалять пользователя

    @WebResult(name = "return")
    @RequestWrapper(localName = "addUser")
    @WebMethod
    @ResponseWrapper(localName = "addUserResponse")
    CompletableFuture<Void> addUser(@WebParam(name = "user") User user);     // 4 добавлять пользователя (с ролями)


    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "updateUser")
    @WebMethod
    @ResponseWrapper(localName = "updateUserResponse")
    CompletableFuture<Void> updateUser(@WebParam(name = "user") User user);                           // 5 редактировать пользователя (с ролями)


}


