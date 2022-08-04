package com.example.PexChat.Service;

import org.springframework.stereotype.Service;



@Service
public class MessengesService extends BaseService {
    
    public Object[] test() {
        return messengesRepo.findByUserId("7d76b4d2-d17b-49f2-b374-58ca7308c73c");
    }
}
