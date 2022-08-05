package com.example.PexChat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.PexChat.Service.MessengesService;
import com.example.PexChat.Service.RoomService;
import com.example.PexChat.Service.UserService;

@Controller
@ResponseBody
public class test {

    @Autowired
    public UserService userService;
    @Autowired
    public RoomService roomService;

    @Autowired
    public MessengesService messengesService;
    // @RequestMapping("/test")
    
    // // public Object testController(Model attr) {
    // //     var x = messengesService.test();
        
    // //     return x;
    // // }
}
