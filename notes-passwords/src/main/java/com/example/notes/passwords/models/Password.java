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
@Table(name = "Passwords")
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "passwordId")
    private int passwordId;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    public Password(String password, User userId){
        this.password = password;
        this.userId = userId;
    }

}
