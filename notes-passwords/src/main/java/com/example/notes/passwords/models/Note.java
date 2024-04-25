package com.example.notes.passwords.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Struct;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "noteId")
    private int noteId;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    public Note(String note, User userId){
        this.note = note;
        this.userId = userId;
    }
}
