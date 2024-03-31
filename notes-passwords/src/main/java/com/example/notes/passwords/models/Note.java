package com.example.notes.passwords.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "userId")
    private int userId;
}
