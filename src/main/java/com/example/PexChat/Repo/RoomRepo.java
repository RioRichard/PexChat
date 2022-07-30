package com.example.PexChat.Repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PexChat.Model.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room,UUID> {
    
}
