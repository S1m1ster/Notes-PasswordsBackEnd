package com.example.notes.passwords.controllers;

import com.example.notes.passwords.dtos.LoginRequest;
import com.example.notes.passwords.models.User;
import com.example.notes.passwords.services.NoteService;
import com.example.notes.passwords.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.LinkedHashMap;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    private UserService us;
    private NoteService ns;

    @PostMapping("/login")
    public ResponseEntity<Object> loginUserRequest(@RequestBody LoginRequest loginRequest, HttpSession session){
        Optional<User> requestedUser = Optional.ofNullable(us.loginUser(loginRequest.getUsername(), loginRequest.getPassword()));

        if(!requestedUser.isPresent()){
            return new ResponseEntity<>( "Incorrect Username or Password",HttpStatus.BAD_REQUEST);
        }
        requestedUser.get().setPassword("");
        session.setAttribute("user", requestedUser.get());

        return new ResponseEntity<>("logged in user: " + requestedUser.get().getUserName() , HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUserRequest(HttpSession session){
        session.removeAttribute("user");
        return new ResponseEntity<>("Logged out user", HttpStatus.OK);
    }

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
    public ResponseEntity<Object>updateUserRequest(@RequestBody LinkedHashMap<String,String> body, HttpSession session){
        try{

            if(session.getAttribute("user") == null){
                return new ResponseEntity<>("Must be logged in.", HttpStatus.LOCKED);
            }
            else{
                int userId = Integer.parseInt(body.get("userId"));
                return new ResponseEntity<>(us.updateUser(userId,body.get("userName"), body.get("firstName"),  body.get("lastName"),  body.get("password"),  body.get("email")), HttpStatus.ACCEPTED);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("failed to update user", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/delete-user-{id}")
    public ResponseEntity<Object>deleteUserRequest(@PathVariable("id") int id, HttpSession session){
        try{
            if(session.getAttribute("user") == null){
                return new ResponseEntity<>("Must be logged in.", HttpStatus.LOCKED);
            }
            else{
                String responseMessage = "User " + id + " has been removed.";
                ns.deleteAllByUserId(id);
                us.deleteUser(id);
                return new ResponseEntity<>(responseMessage, HttpStatus.ACCEPTED);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("failed to delete user", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
