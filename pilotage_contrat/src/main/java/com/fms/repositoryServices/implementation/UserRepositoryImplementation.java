package com.fms.repositoryServices.implementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fms.entity.Users;
import com.fms.repositoryServices.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class UserRepositoryImplementation {
    @Autowired
    private UserRepository userRepository;
    
    
    public Users create(Users user) {
        log.info("Registering new user");
        if(emailTaken(user))
            throw new IllegalStateException("Email Taken!");
        return userRepository.save(user);
       
    }
    
    public List<Users> getUsers(){
        log.info("Fetching users");
        return userRepository.findAll();
    }
    
    public Users getById(long id) {
        log.info("Getting the user with " + id);
        return userRepository.findById(id).get();
    }
    
    public Optional<Users> findById(long id){
        return userRepository.findById(id);
    }
    
    public Optional<Users> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    
    @Transactional
    public Users updateUser(Users user) {
        log.info("Updating user ", user);
        Optional<Users> users = userRepository.findById(user.getId());
        if(users.isPresent()) {
            if(!user.getEmail().equals(users.get().getEmail()) && emailTaken(user)) {
                    throw new IllegalStateException("Email Taken!");                             
            }
            return userRepository.save(user);
        }
        throw new NoSuchElementException("User not found!");
    }
    
    public Boolean delete(long id) {
        log.info("Deleting user by id: ", id );
        userRepository.deleteById(id);
        return true;
    }
        
    
    
    private boolean emailTaken(Users user) {
        //"Clearer way
        //Optional<Users> usersByEmail = userRepository.findUsersByEmail(user.getEmail();
        //return usersByEmail.isPresent();
        return userRepository.findByEmail(user.getEmail()).isPresent();
    }

}







