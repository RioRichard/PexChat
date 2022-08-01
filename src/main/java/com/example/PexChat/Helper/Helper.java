package com.example.demo.Helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Random;



public class Helper{

    public static byte[] Hash(String plainText)  {
        
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
             return md.digest(plainText.getBytes());

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException(e);
        }
    }
    public static String RandomString(int length) {
        String p = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
        char[] a = new char[length];
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = p.charAt(random.nextInt(p.length()));
        }
        return String.valueOf(a);
    }



  

    
} 
