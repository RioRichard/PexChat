package com.example.PexChat.Repo;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.PexChat.Model.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room,UUID> {
    
    @Query(value = "select TOP 1 user1.room_id from(select room_id from Messages m where m.user_id = :id1) user1 join (select room_id from Messages m where m.user_id = :id2) user2 on user1.room_id = user2.room_id", nativeQuery = true)
    public  String findSameRoomOf2User(@Param("id1") String userId1, @Param("id2") String userId2);
}
