package com.example.notes.passwords.repos;

import com.example.notes.passwords.models.Note;
import com.example.notes.passwords.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note, String> {
    public List<Note> findAllNotesByUserId(User userId);

    public List<Note> deleteAllNotesByUserId(User userId);

    public Note findByNoteId(int noteId);
}
