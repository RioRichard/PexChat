package com.example.PexChat.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.PexChat.Model.Users;
import com.example.PexChat.Repo.MessengesRepo;
import com.example.PexChat.Repo.RoomRepo;
import com.example.PexChat.Repo.UserRepo;

public abstract class BaseService {
    @Autowired
    protected UserRepo userRepo;

    @Autowired 
    protected RoomRepo roomRepo;

    @Autowired
    protected MessengesRepo messengesRepo;

    
    public Users getCurrentUser(){
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepo.findByUsername(userName);     
        return user;
    }
}
