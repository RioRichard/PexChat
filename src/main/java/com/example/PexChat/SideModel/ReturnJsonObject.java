package com.example.PexChat.SideModel;

public class ReturnJsonObject {
    public boolean Status;
    public String Result;
    public String url;
    public ReturnJsonObject(boolean status, String result, String url) {
        Status = status;
        Result = result;
        this.url = url;
    }
    

    
}
