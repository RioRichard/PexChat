package com.example.PexChat.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.PexChat.Repo.UserRepo;

public abstract class BaseService {
    @Autowired
    protected UserRepo userRepo;
}