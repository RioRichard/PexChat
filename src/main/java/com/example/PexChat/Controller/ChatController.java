package com.example.PexChat.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.PexChat.Model.Room;
import com.example.PexChat.Service.MessengesService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.google.gson.Gson;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController extends BaseController {
    

    
    @GetMapping("/")
    public String home(Model model) {   
        model.addAttribute("listRoom", roomService.getRooms());   
        return "homepage";
    }
    

    @ResponseBody
    @GetMapping("/test")
    public String name() {
        Gson gson = new Gson();
        return gson.toJson(messengesService.test());
    }

    @SubscribeMapping("/room")
    public List<Room> rooms() {
        return roomService.getRooms();
    }
}

    
