package com.example.PexChat.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.PexChat.Model.Messenges;
import com.example.PexChat.Model.Room;

@Service
public class MessengesService extends BaseService {

    public List<Room> test() {

        List<Object[]> res = messengesRepo.findByUserId("7d76b4d2-d17b-49f2-b374-58ca7308c73c");
        List<Room> list = new ArrayList<>();
        

        for (Object[] object : res)  {
            
            Room eq = new Room();
            eq.setRoom_id(UUID.fromString(object[0].toString()));
            eq.setRoom_name(object[1].toString());
            eq.setDate_created(Date.valueOf(object[2].toString()));
            // And set all the Equip fields here
            // And last thing add it to the list

            list.add(eq);
        }
        return list;
    }
    
    public void addMesseges(Messenges msgs) {
        messengesRepo.save(msgs);
    }

}
