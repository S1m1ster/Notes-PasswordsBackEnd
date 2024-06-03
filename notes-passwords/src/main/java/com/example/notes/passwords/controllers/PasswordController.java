package com.example.notes.passwords.controllers;

import com.example.notes.passwords.models.Password;
import com.example.notes.passwords.services.PasswordService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/api/v1/user/password")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
public class PasswordController {
    private PasswordService ps;
    @PostMapping("/create-password")
    public ResponseEntity<Object> createPasswordRequest(@RequestBody LinkedHashMap<String, String> body, HttpSession session){
        try{
            if(session.getAttribute("user") == null){
                return new ResponseEntity<>("Must be logged in.", HttpStatus.LOCKED);
            }
            else{
                int userId = Integer.parseInt(body.get("userId"));

                return new ResponseEntity<>(ps.createPassword(body.get("password"), userId), HttpStatus.CREATED);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to create password", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/update-password")
    public ResponseEntity<Object> updatePasswordRequest(@RequestBody LinkedHashMap<String, String> body, HttpSession session){
        try{
            if(session.getAttribute("user") == null){
                return new ResponseEntity<>("Must be logged in.", HttpStatus.LOCKED);
            }
            else{
                int passwordId = Integer.parseInt(body.get("passwordId"));

                return new ResponseEntity<>(ps.updatePassword(body.get("password"), passwordId), HttpStatus.ACCEPTED);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update password", HttpStatus.EXPECTATION_FAILED);
        }
    }
    @DeleteMapping("/delete-password-{id}")
    public ResponseEntity<Object> deletePasswordByIdRequest(@PathVariable("id") int id, HttpSession session){
        try{
            if(session.getAttribute("user") == null){
                return new ResponseEntity<>("Must be logged in.", HttpStatus.LOCKED);
            }
            else{
                String responseMessage = "Password " + id + " has been removed.";
                ps.deletePasswordById(id);
                return new ResponseEntity<>(responseMessage, HttpStatus.ACCEPTED);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete password", HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping("/{id}-passwords")
    public ResponseEntity<Object> getAllPasswordsByUserIdRequest(@PathVariable("id")int id, HttpSession session){
        try{
            if(session.getAttribute("user") == null){
                return new ResponseEntity<>("Must be logged in.", HttpStatus.LOCKED);
            }
            else{
                return new ResponseEntity<>(ps.getAllPasswordsOfUser(id), HttpStatus.ACCEPTED);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to retrieve users passwords", HttpStatus.EXPECTATION_FAILED);
        }
    }



}
