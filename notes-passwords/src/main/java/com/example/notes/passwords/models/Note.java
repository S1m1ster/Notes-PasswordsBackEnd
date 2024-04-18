package com.example.notes.passwords.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Notes")
public class Note {
    @Id
    @Column(name = "noteId")
    private int noteId;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User getUserId;
}
