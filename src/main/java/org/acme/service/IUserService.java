package org.acme.service;

import org.acme.model.User;
import org.acme.model.UserRequest;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IUserService {
    public User addUser(UserRequest request) throws NoSuchAlgorithmException;
    public User updateUser(UserRequest request) throws NoSuchAlgorithmException;

}
