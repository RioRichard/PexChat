package com.example.PexChat.Service;

import org.springframework.stereotype.Service;



@Service
public class MessengesService extends BaseService {
    
    public Object test() {
        return messengesRepo.findAll();
    }
}
