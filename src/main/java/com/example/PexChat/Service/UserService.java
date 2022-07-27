package com.example.PexChat.Service;


import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.PexChat.Model.Users;

@Service
public class UserService extends BaseService {
    public Users testUser(){
        
        return userRepo.findById(UUID.fromString("7d76b4d2-d17b-49f2-b374-58ca7308c73c")).get();
    }
    

}
