package com.example.notes.passwords.repos;

import com.example.notes.passwords.models.Password;
import com.example.notes.passwords.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordRepo extends JpaRepository<Password, String> {
    public Password findByPasswordId(int passwordId);

    public List<Password> findAllPasswordsByUserId(User userId);

}
