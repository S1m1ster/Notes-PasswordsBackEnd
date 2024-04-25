package com.example.notes.passwords.controllers;

import com.example.notes.passwords.services.NoteService;
import com.example.notes.passwords.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    private UserService us;
    private NoteService ns;

    @PostMapping("/create-user")
    public ResponseEntity<Object>createUserRequest(@RequestBody LinkedHashMap<String,String> body){
        try{
            return new ResponseEntity<>(us.createUser(body.get("userName"), body.get("firstName"),  body.get("lastName"),  body.get("password"),  body.get("email")), HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("failed to create new user", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/update-user")
    public ResponseEntity<Object>updateUserRequest(@RequestBody LinkedHashMap<String,String> body){
        try{
            int userId = Integer.parseInt(body.get("userId"));

            return new ResponseEntity<>(us.updateUser(userId,body.get("userName"), body.get("firstName"),  body.get("lastName"),  body.get("password"),  body.get("email")), HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("failed to update user", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/delete-user-{id}")
    public ResponseEntity<Object>deleteUserRequest(@PathVariable("id") int id){
        try{
            String responseMessage = "User " + id + " has been removed.";
            ns.deleteAllByUserId(id);
            us.deleteUser(id);
            return new ResponseEntity<>(responseMessage, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("failed to delete user", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
