
package com.example.PexChat.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.PexChat.Service.MessengesService;
import com.example.PexChat.Service.RoomService;
import com.example.PexChat.Service.UserService;

public class BaseController {
    @Autowired
    protected MessengesService messengesService;
    @Autowired
    protected RoomService roomService;
    
    @Autowired
    protected UserService userService;
}
    
