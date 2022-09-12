package com.challange.tenpo.services;

import com.challange.tenpo.dtos.UserDTO;
import com.challange.tenpo.entitys.User;
import com.challange.tenpo.exceptions.UserAlreadyExistException;

import java.net.URI;

public interface UserService {

    User registerUser(UserDTO userDTO) throws UserAlreadyExistException;
    URI buildSingUpUserUriCreated();
    User getUserByUsername(String username);
    User getUserByEmail(String username);
    boolean isUserRegistered(String username);
    boolean isEmailRegistered(String email);

}