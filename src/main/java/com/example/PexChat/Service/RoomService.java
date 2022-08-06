package com.example.PexChat.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.PexChat.Model.Room;

@Service
public class RoomService extends BaseService {
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

    public List<Room> getRooms() {
        var user = super.getCurrentUser();
        List<Object[]> res = messengesRepo.findByUserId(user.getUser_id().toString());

        return getRoomsFromQuery(res);
    }
}
