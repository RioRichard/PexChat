package com.example.PexChat.SideModel;

import com.example.PexChat.Model.Users;
import com.example.PexChat.Repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomDetailService implements UserDetailsService {

    @Autowired
    UserRepo UserRepos;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = UserRepos.findByUser(username);
        if(user == null)
            throw new UsernameNotFoundException("User not fount");
        return new CustomDetail(user);
    }
    
}
