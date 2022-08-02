package com.example.PexChat.Service;

import org.apache.catalina.User;
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
}
