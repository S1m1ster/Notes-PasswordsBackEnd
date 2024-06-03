package com.example.notes.passwords.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswords {
    private int passwordId;
    private String passsword;
}
