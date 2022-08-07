package com.example.PexChat.Controller;

import java.io.IOException;
import java.sql.Date;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.PexChat.Helper.Helper;
import com.example.PexChat.Model.Users;

import com.example.PexChat.Service.UserService;
import com.example.PexChat.SideModel.ChangePassword;
import com.example.PexChat.SideModel.ReturnJsonObject;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import lombok.var;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller

public class AccountController {
    private long copy;
    @RequestMapping("/resigter")
    String register(Model model) {
        model.addAttribute("something", "some thing from controller");
        return "resigterPage";
    }

    @Autowired
    private UserService userService;

    @PostMapping(value = "/Resigter")
    String addAccount(@ModelAttribute("user") Users user, RedirectAttributes redirAttrs) throws IOException {
        var checkUser = userService.findByUser(user.getUsername());
        if (checkUser != null) {
            redirAttrs.addFlashAttribute("error", "Username đã tồn tại");
            return "redirect:/resigter";
        } else {
            user.setUser_id(UUID.randomUUID());
            user.setAvartar("default-avatar.jpg");
            user.setBackup_code(Helper.RandomString(8));
            Date now = new Date(System.currentTimeMillis());
            user.setDate_created(now);
            String pass = "";
            System.out.println("username :" + user.getUsername());
            System.out.println("password :" + user.getPassword());
            String string = new String(user.getPassword());
            for (var x : string.split("")) {
                pass += x;
                System.out.println(x);
            }
            // System.out.println(Helper.Hash(pass));
            user.setPassword(Helper.Hash(pass));
            userService.saveUser(user);
            System.out.println("password :" + user.getPassword());
            redirAttrs.addFlashAttribute("success", "Đăng kí thành công, hãy tiến hành đăng nhập");
            return "redirect:/resigter";
        }

    }

    @RequestMapping("/login")
    String login(Model model) {
        return "loginPage";
    }
    // @PostMapping (value = "/login")
    // String login(@ModelAttribute Users user) throws IOException{
    // System.out.println("username: "+user.getUsername());
    // System.out.println("pass: "+user.getPassword());
    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();
    // if (authentication == null || authentication instanceof
    // AnonymousAuthenticationToken) {
    // return "loginPage";
    // }
    // return "redirect:/";

    // }
    // @ResponseBody
    @RequestMapping("/LogSuccess")
    public String LogSuccess() {
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping("/FailureLog")
    public Object FailureLog() {

        return new ReturnJsonObject(false, "Đăng nhập thất bại, sai tài khoản hoặc mật khẩu", null);
    }

    // setting profile
    @RequestMapping("/accounts")
    String setting(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "loginPage";
        }
        model.addAttribute("info", userService.GetCurrentUser());
        return "SettingPage";
    }

    @RequestMapping("/password")
    String Security(Model model) {
        model.addAttribute("something", "");
        return "SecurityPage";
    }

    @PostMapping("/changepassword")
    String ChangePassword(@ModelAttribute("password") ChangePassword password, Model model) {
        model.addAttribute("something", userService.ChangePassword(password));
        System.out.println(password.getOldPass());
        System.out.println(password.getNewPass());
        return "SecurityPage";
    }
    
    @PostMapping("/uploadavata")
    String UploadAvata( @RequestParam("avartar") MultipartFile MultiPartFile) throws  IOException{
        String fileName=StringUtils.cleanPath(MultiPartFile.getOriginalFilename());
        var user = userService.GetCurrentUser();
        user.setAvartar(fileName);
        userService.saveUser(user);
        String uploadDir="./src/main/resources/static/Image/";

        Path uploadPath=Paths.get(uploadDir);

        if(!Files.exists(uploadPath))
        {
            Files.createDirectories(uploadPath);
        }
        try {
            InputStream inputStream=MultiPartFile.getInputStream();
        Path filepPath=uploadPath.resolve(fileName);
        copy = Files.copy(inputStream,filepPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new IOException("Upload thất bại");
        }
        return "redirect:/accounts";

    }
}
