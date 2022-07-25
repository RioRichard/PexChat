package com.example.PexChat.Service;


import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.PexChat.Model.Users;

@Service
public class UserService extends BaseService {
    public Users testUser(){
        UUID id = UUID.fromString("7d76b4d2-d17b-49f2-b374-58ca7308c73c");
        return userRepo.getReferenceById(id);
    }
    

}
