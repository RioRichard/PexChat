package com.example.PexChat.Service;


import java.sql.Date;
import java.util.UUID;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import com.example.PexChat.Model.Users;


@Service
public class UserService extends BaseService {
    public Users testUser(){
        
        return userRepo.findById(UUID.fromString("7d76b4d2-d17b-49f2-b374-58ca7308c73c")).get();
    }
    public void saveUser(Users user){
        userRepo.save(user);
    }

    public Users GetUser(String id){
        return userRepo.findById(UUID.fromString(id)).get();
    }
}
