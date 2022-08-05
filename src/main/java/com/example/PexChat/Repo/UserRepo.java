package com.example.PexChat.Repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PexChat.Model.Users;
@Repository
public interface UserRepo extends JpaRepository<Users, UUID> {
    public Users findByUsername(String username);
    public Users findByUsernameAndPassword(String username, String Password);
    
}
