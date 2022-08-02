package com.example.PexChat.Controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.PexChat.Helper.Helper;
import com.example.PexChat.Model.Users;
import com.example.PexChat.Repo.UserRepo;
import com.example.PexChat.Service.UserService;
import com.example.PexChat.SideModel.ChangePassword;
import com.example.PexChat.SideModel.ReturnJsonObject;

import lombok.var;

@Controller
public class AccountController {
    @RequestMapping("/resigter")
    String register(Model model) {
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
        String pass = "";
        System.out.println("username :"+user.getUsername());
        for (var x : user.getPassword()) {
            pass+=x;
        }
        user.setPassword(Helper.Hash(pass));
        userService.saveUser(user);
        
        System.out.println(now);
        return "redirect:/";
    }
    @RequestMapping("/login")
    String login(Model model) {
        model.addAttribute("something", "some thing from controller");
        return "loginPage";
    }
    // @PostMapping (value = "/login")
    // String login(@ModelAttribute Users user) throws IOException{
    //     System.out.println("username: "+user.getUsername());
    //     System.out.println("pass: "+user.getPassword());
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
    //         return "loginPage";
    //     }
    //     return "redirect:/";
        
    // }
    @ResponseBody
    @RequestMapping("/LogSuccess")
    public Object LogSuccess() {
        return new ReturnJsonObject(true, "Đăng nhập thành công, bạn sẽ quay về trang chủ", "/");
    }

    @ResponseBody
    @RequestMapping("/FailureLog")
    public Object FailureLog() {

        return new ReturnJsonObject(false, "Đăng nhập thất bại, sai tài khoản hoặc mật khẩu", null);
    }

    // setting profile
    @RequestMapping("/accounts")
    String setting(Model model) {
        model.addAttribute("info",userService.GetCurrentUser());
        return "SettingPage";
    }

    @RequestMapping("/password")
    String Security(Model model) {
        model.addAttribute("something","");
        return "SecurityPage";
    }

    @PostMapping("/changepassword")
    String ChangePassword(@ModelAttribute("password")  ChangePassword password , Model model){
        model.addAttribute("something",userService.ChangePassword(password));
        System.out.println(password.getOldPass());
        System.out.println(password.getNewPass());
        return "SecurityPage";
    }
}
