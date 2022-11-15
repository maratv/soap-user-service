package soapuserservice.service;

import soapuserservice.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

@WebService
public interface UserWebService {


    @WebResult(name = "return")
    @RequestWrapper(localName = "sayHello") //,
    @WebMethod(action = "urn:SayHello")
    @ResponseWrapper(localName = "sayHelloResponse")
    String sayHello(@WebParam(name = "name") String name);                     // тестовый

    @WebResult(name = "user")
    @RequestWrapper(localName = "getAllUsers")
    @WebMethod
    @ResponseWrapper(localName = "getAllUsersResponse")
    List<User> getAllUsers();                                    // 1 Получать список без ролей


    @WebResult(name = "user")
    @RequestWrapper(localName = "getUserByName")
    @WebMethod
    @ResponseWrapper(localName = "getUserByNameResponse")
    User getUserByName(@WebParam(name = "name") String name);   // 2 Получать конкретного пользователя по имени

    @WebResult(name = "return")
    @RequestWrapper(localName = "deleteUserByName")
    @WebMethod
    @ResponseWrapper(localName = "deleteUserByNameResponse")
  //  void deleteUserByName(@WebParam(name = "name") String name);    // 3 Удалять пользователя по имени
    ValidationResponse deleteUserByName(@WebParam(name = "name") String name);    // 3 Удалять пользователя по имени


    @WebResult(name = "return")
    @RequestWrapper(localName = "addUser")
    @WebMethod
    @ResponseWrapper(localName = "addUserResponse")
    ValidationResponse addUser(@WebParam(name = "user") User user);             // 4 добавлять пользователя (с ролями)

    @WebResult(name = "return")
    @RequestWrapper(localName = "updateUser")
    @WebMethod
    @ResponseWrapper(localName = "updateUserResponse")
    void updateUser(@WebParam(name = "user") User user);           // 5 редактировать пользователя (с ролями)
}


