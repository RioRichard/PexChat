package com.example.PexChat.SideModel;

import java.nio.charset.StandardCharsets;

import com.example.PexChat.Helper.Helper;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPassEncode implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        var raw = rawPassword.toString();
        var hash = Helper.Hash(raw);
        return new String(hash,StandardCharsets.UTF_8);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        var raw = rawPassword.toString();
        var hash = Helper.Hash(raw);
        var encodedRaw = new String(hash,StandardCharsets.UTF_8);
        return encodedRaw.equals(encodedPassword);
    }
    
}
