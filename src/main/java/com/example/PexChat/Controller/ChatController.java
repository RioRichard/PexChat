package com.example.PexChat.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
    
    @GetMapping("/")
    public String home() {
        return "homepage";
    }
}
