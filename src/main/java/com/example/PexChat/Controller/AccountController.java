package com.example.PexChat.Controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.example.PexChat.Model.Users;
import com.example.PexChat.Service.UserService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class AccountController {
    @RequestMapping("/resigter")
    String login(Model model) {
        model.addAttribute("something", "some thing from controller");
        return "resigterPage";
    }

    @Autowired
    private UserService userService;

    @PostMapping(value = "/Resigter")
    String addAccount(@ModelAttribute("user") Users user) throws IOException {
        user.setUser_id(UUID.randomUUID());
        user.setAvartar("avartar");
        user.setBackup_code("123");
        Date now = new Date(System.currentTimeMillis());
        user.setDate_created(now);
        // System.out.println(user.getPassword());
        userService.saveUser(user);
        System.out.println(now);
        return "redirect:/";
    }

    // setting profile
    @RequestMapping("/Setting")
    String setting(Model model) {
        model.addAttribute("info",userService.GetUser("1b541af7-3d0c-4fad-8be4-25276b55380d"));
        return "SettingPage";
    }

    @GetMapping("Security")
    String Security() {
        return "SecurityPage";
    }
}
