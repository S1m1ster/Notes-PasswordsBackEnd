package com.example.notes.passwords.services;

import com.example.notes.passwords.models.User;
import com.example.notes.passwords.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private UserRepo ur;

    public User createUser(String userName, String firstName, String lastName, String password, String email){
        User newUser = new User(userName, firstName, lastName, password, email);
        return ur.save(newUser);
    }

    public Optional<User> updateUser(int id, String userName, String firstName, String lastName, String password, String email){
        return ur.findById(String.valueOf(id)).map(u -> {
                    u.setUserName(userName);
                    u.setFirstName(firstName);
                    u.setLastName(lastName);
                    u.setPassword(password);
                    u.setEmail(email);
                    return u;
                }
        );
    }

    public void deleteUser(int id){
        ur.deleteById(String.valueOf(id));
    }

}
