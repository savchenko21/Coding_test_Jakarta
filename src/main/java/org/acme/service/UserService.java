package org.acme.service;

import jakarta. enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.model.User;
import org.acme.model.UserRequest;
import org.acme.repository.UserRepository;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@ApplicationScoped // Only one instance of a UserService class is created at a lifetime of the application

public class UserService implements IUserService{    // UserService class implements interface IUserService
    @Inject UserRepository userRepository;           // Injecting an instance of UserRepository class

    @Transactional // database operations performed within addUser() method are executed within a transaction
    public User addUser(UserRequest request) throws NoSuchAlgorithmException {

        User entity= new User();                         // creating an instance of a User class
        entity.setFirstname(request.getFirstname());     // setting properties of User instance to values of a passed UserRequest
        entity.setLastname(request.getLastname());
        entity.setEmail(request.getEmail());
        entity.setBirthday(request.getBirthday());

        String strSalt = generateSalt();                // calling generateSalt() method and assigning its return value to strSalt
        entity.setSalt(strSalt);                        // setting strSalt as a value of salt property

        String strPassword = hashPassword(request.getPassword(),strSalt); // calling hashPassword() with a password property of
                                                                          // UserRequest request and a generated strSalt
        entity.setPassword(strPassword);                                  // setting strPassword as a value of password property

        userRepository.persist(entity); // saving entity to the database
        return entity;

}

    @Transactional // database operations performed within addUser() method are executed within a transaction
    public User updateUser(UserRequest request) throws NoSuchAlgorithmException {

        User entity= userRepository.findByEmail(request.getEmail());     // calling findByEmail method defined in userRepository

        String strSalt = generateSalt();       // calling generateSalt() method and assigning its return value to strSalt
        entity.setSalt(strSalt);               // setting strSalt as a value of salt property

        String strPassword = hashPassword(request.getPassword(),strSalt); // calling hashPassword() with a password property of
                                                                          // UserRequest request and a generated strSalt
        entity.setPassword(strPassword);                                  // setting strPassword as a value of password property
        entity.setBirthday(request.getBirthday());
        entity.setEmail(request.getEmail());
        entity.setLastname(request.getLastname());
        entity.setFirstname(request.getFirstname());

        userRepository.persist(entity); // saving entity to the database
        return entity;
    }
    /* In order to secure user's password a salt is added to the input of a hash function.
     * This way not only is a hash value generated out of the password, but also
     * a unique sequence of pseudo-random bytes is passed to a hash function,
     * so that hash is generated out of a password with the salt. Both hash and salt are stored in a database.
     *
     * The generateSalt() method is called in addUser() and updateUser() methods each time providing a new salt */
    public String generateSalt(){
        SecureRandom random = new SecureRandom(); // creating an instance of a SecureRandom class
        byte[] salt = new byte [16];              // creating an empty byte array of length 16
        random.nextBytes(salt);                   // generating pseudo-random bytes
        return new String(salt);                  // creating a String object out of the byte array and returning it
    }
    public String hashPassword(String password,String strSalt) throws NoSuchAlgorithmException {
        byte[] bsalt=strSalt.getBytes();                                  // Converting String object to a byte array
        MessageDigest md = MessageDigest.getInstance("SHA-512"); // Creating a MessageDigest (MD) object and applying SHA-512 algorithm
        md.update(bsalt);                                                 // passing salt to the MD object
        byte[] byteData = md.digest(password.getBytes());                 // passing password to the MD Object and computing the hash function
                                                                          // assigning message digest to byteData

        md.reset();
        return new String(byteData);   // creating a String object out of the byte array and returning it
    }
}