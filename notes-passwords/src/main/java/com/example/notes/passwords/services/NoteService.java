package com.example.notes.passwords.services;

import com.example.notes.passwords.models.Note;
import com.example.notes.passwords.repos.NoteRepo;
import com.example.notes.passwords.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class NoteService {
    private NoteRepo nr;
    private UserRepo ur;

    public Note createNote(int userId, String note){
        try{
            Note createdNote = new Note(note, ur.findByUserId(userId));
            return nr.save(createdNote);
        }
        catch(NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Note> getAllNotesByUserId(int userId){
        try{
            return nr.findAllNotesByUserId(ur.findByUserId(userId));
        }
        catch(NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public void deleteAllByUserId(int userId){
        try{
            nr.deleteAllNotesByUserId(ur.findByUserId(userId));
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    public void deleteNoteById(int noteId){
        try{
            nr.deleteById(String.valueOf(noteId));
        }
        catch(NullPointerException e){
            System.out.println("Could not find note with id of: " + noteId);
            e.printStackTrace();
        }
    }

    public Note updateNoteById(int noteId, String note){
        try{
            Note updatedNote = nr.findByNoteId(noteId);
            updatedNote.setNote(note);
            return updatedNote;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
