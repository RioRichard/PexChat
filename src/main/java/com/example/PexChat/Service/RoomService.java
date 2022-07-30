package com.example.PexChat.Service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class RoomService extends BaseService {
    public Object getname() {
        return roomRepo.findById(UUID.fromString("7d76b4d2-d17b-49f2-b374-58ca7308c73c")).get();

    }
}
