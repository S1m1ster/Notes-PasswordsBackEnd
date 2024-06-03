package com.example.notes.passwords.controllers;

import com.example.notes.passwords.services.NoteService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user/notes")
@CrossOrigin("http://localhost:3000")
public class NoteController {
    private NoteService ns;

    @PostMapping("/create-note")
    public ResponseEntity<Object> createNoteRequest(@RequestBody LinkedHashMap<String,String> body, HttpSession session){
        try{
            if(session.getAttribute("user") == null){
                return new ResponseEntity<>("Must be logged in.", HttpStatus.LOCKED);
            }
            else{
                int userId = Integer.parseInt(body.get("userId"));
                return new ResponseEntity<>(ns.createNote(userId, body.get("note")), HttpStatus.CREATED);
            }

        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("failed to create new note ", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/{id}-notes")
    public ResponseEntity<Object> getAllNotesByUserIdRequest(@PathVariable("id")int id, HttpSession session){
        try{
            if(session.getAttribute("user") == null){
                return new ResponseEntity<>("Must be logged in.", HttpStatus.LOCKED);
            }
            else{
                return new ResponseEntity<>(ns.getAllNotesByUserId(id), HttpStatus.ACCEPTED);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to retrieve users notes", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/delete-note-{id}")
    public ResponseEntity<Object>deleteNoteByIdRequest(@PathVariable("id") int id, HttpSession session){
        try{
            if(session.getAttribute("user") == null){
                return new ResponseEntity<>("Must be logged in.", HttpStatus.LOCKED);
            }
            else{
                String responseMessage = "Note " + id + " has been removed.";
                ns.deleteNoteById(id);
                return new ResponseEntity<>(responseMessage, HttpStatus.ACCEPTED);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("failed to delete note", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/update-note")
    public ResponseEntity<Object>updateUserRequest(@RequestBody LinkedHashMap<String,String> body, HttpSession session){
        try{
            if(session.getAttribute("user") == null){
                return new ResponseEntity<>("Must be logged in.", HttpStatus.LOCKED);
            }
            else{
                int noteId = Integer.parseInt(body.get("noteId"));

                return new ResponseEntity<>(ns.updateNoteById(noteId, body.get("note")) , HttpStatus.ACCEPTED);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("failed to update user", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
