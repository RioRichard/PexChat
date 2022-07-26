package com.example.PexChat.Repo;

import java.util.List;
import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.PexChat.Model.Messenges;
import com.example.PexChat.Model.Room;


@Repository
public interface MessengesRepo extends JpaRepository<Messenges,UUID>{
    @Query(value = "select r.* from (select m.room_id as id from messages m where m.user_id = :id group by m.room_id) as a inner join room r on a.id = r.room_id ", nativeQuery = true)
    public  List<Object[]> findByUserId(@Param("id") String userId);

    public List <Messenges> findByRoomOrderByDate(Room room); 
    @Query(value = "select m.user_id from Messages m where m.room_id = :id group by user_id", nativeQuery = true)
    public  List<String> findUserIdGroupByRoomId(@Param("id") String roomId);
}
