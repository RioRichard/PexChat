package com.example.PexChat.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.PexChat.Model.Room;

@Service
public class RoomService extends BaseService {

    public Object test() {
        return roomRepo.findSameRoomOf2User("26cf5c8c-34e4-40ab-ad8d-2193d1944b40", "c78add34-a17e-4c8c-99ea-cf8137fe25df");
    }
    
    public Object getname() {
        return roomRepo.findById(UUID.fromString("7d76b4d2-d17b-49f2-b374-58ca7308c73c")).get();

    }

    public List<Room> getRoomsFromQuery(List<Object[]> listRoomFromQuery) {

        List<Room> list = new ArrayList<>();

        for (Object[] object : listRoomFromQuery) {

            Room room = new Room();
            room.setRoom_id(UUID.fromString(object[0].toString()));
            room.setRoom_name(object[1].toString());
            room.setDate_created(Date.valueOf(object[2].toString())); 
            list.add(room);
        }
        return list;
    }

    public List<Room> getRooms(String username) {
        var user_id = userRepo.findByUsername(username);
        
        List<Object[]> res = messengesRepo.findByUserId(user_id.getUser_id().toString());
        // List<Object[]> res = messengesRepo.findByUserId("7d76b4d2-d17b-49f2-b374-58ca7308c73c");


        return getRoomsFromQuery(res);
    }
    
    public void addRoom(Room room){
        roomRepo.save(room);
    }
    public String getSameRoom(String userId1,String userId2) {
        return roomRepo.findSameRoomOf2User(userId1, userId2);
    }
    public Room getRoom(UUID room_id) {
        return roomRepo.findById(room_id).get();
    }
    
}
