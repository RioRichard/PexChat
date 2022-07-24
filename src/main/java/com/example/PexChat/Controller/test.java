package com.example.PexChat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.PexChat.Repo.UserRepo;
import com.example.PexChat.Service.UserService;

@Controller
public class test {

    @Autowired
    public UserService userService;
    @RequestMapping("/test")
    public String testController(Model attr) {
        var x = userService.testUser();
        attr.addAttribute("test",userService.testUser());
        System.out.println(x);
        return "/homepage";
    }
}
