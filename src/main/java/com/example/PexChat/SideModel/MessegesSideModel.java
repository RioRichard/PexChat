package com.example.PexChat.SideModel;

public class MessegesSideModel {
    String room_id;
    String content;
    String user_id;
    public MessegesSideModel(String room_id, String content, String user_id) {
        this.room_id = room_id;
        this.content = content;
        this.user_id = user_id;
    }
    public MessegesSideModel() {
    }
    public String getRoom_id() {
        return room_id;
    }
    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    
    
}
