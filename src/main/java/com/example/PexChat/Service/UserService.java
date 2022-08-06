package com.example.PexChat.Service;


import java.sql.Date;
import java.util.Arrays;
import java.util.UUID;

import org.apache.catalina.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.stereotype.Service;

import com.example.PexChat.Helper.Helper;
import com.example.PexChat.Model.Users;
import com.example.PexChat.SideModel.ChangePassword;
import com.example.PexChat.SideModel.ReturnJsonObject;


@Service
public class UserService extends BaseService {
    public Users testUser(){
        
        return userRepo.findById(UUID.fromString("7d76b4d2-d17b-49f2-b374-58ca7308c73c")).get();
    }
    public void saveUser(Users user){
        userRepo.save(user);
    }
    public Users findByUser(String username){
        return userRepo.findByUsername(username);
    }
    public Users GetUser(String id){
        return userRepo.findById(UUID.fromString(id)).get();
    }

    public Users GetCurrentUser(){
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepo.findByUsername(userName);     
        return user;
    }

    public String ChangePassword(ChangePassword changepassword){
        var user = super.getCurrentUser();
        var check = Arrays.equals(user.getPassword(), Helper.Hash(changepassword.getOldPass()));
        if(check){
            user.setPassword(Helper.Hash(changepassword.getNewPass()));
            userRepo.saveAndFlush(user);
            return "Đổi mật khẩu thành công";
        }else{
            return "Sai mật khẩu cũ";
        }
    }
}
