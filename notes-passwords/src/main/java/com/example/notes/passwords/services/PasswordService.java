package com.example.notes.passwords.services;

import com.example.notes.passwords.daos.UserPasswords;
import com.example.notes.passwords.models.Password;
import com.example.notes.passwords.repos.PasswordRepo;
import com.example.notes.passwords.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Transactional
@AllArgsConstructor
public class PasswordService {
    private PasswordRepo pr;
    private UserRepo ur;

    public Password createPassword(String password, int userId){
        try{
            Password newPassword = new Password(password, ur.findByUserId(userId));
            return pr.save(newPassword);
        }
        catch(NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }
    public Password updatePassword(String password, int passwordId){
        try{
            Password updatedPassword = pr.findByPasswordId(passwordId);
            updatedPassword.setPassword(password);
            return updatedPassword;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void deletePasswordById(int passwordId){
        try{
            pr.deleteById(String.valueOf(passwordId));
            System.out.println("Password with Id: " + passwordId + " Has been deleted");
        }
        catch(Exception e){
            System.out.println("Could not find password with Id of " + passwordId);
            e.printStackTrace();
        }
    }

    public List<UserPasswords> getAllPasswordsOfUser(int userId){
        try{
            AtomicInteger i= new AtomicInteger();

            List<UserPasswords> found = new ArrayList<>();
            pr.findAllPasswordsByUserId(ur.findByUserId(userId)).forEach(password -> {
                found.add(Integer.parseInt(String.valueOf(i)), new UserPasswords(password.getPasswordId(), password.getPassword()));
                i.getAndIncrement();
            });
            return found;
        }
        catch(Exception e){
            System.out.println("Cannot delete passwords of User: " + userId);
            e.printStackTrace();
            return null;
        }
    }
}
