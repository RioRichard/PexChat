package com.example.PexChat.SideModel;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.example.PexChat.Model.Users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomDetail implements UserDetails {

    private Users user;

    public CustomDetail(Users user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add( new SimpleGrantedAuthority("ROLE_" + this.user)) ;
        return roles;
    }

    public UUID getUserId(){
        return this.user.getUser_id();
    }

    @Override
    public String getPassword() {

        return new String(this.user.getPassword(),StandardCharsets.UTF_8);
        
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
