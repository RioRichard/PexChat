package com.example.PexChat.SideModel;

public class MessegesSideModel {
    public static final int MESSAGE = 1;
    public static final int IMAGE = 2;
    public static final int JOIN = 3;
    
    String room_id;
    String content;
    String user_id;
    int msg_type;
    
    
    public MessegesSideModel(String room_id, String content, String user_id, int msg_type) {
        this.room_id = room_id;
        this.content = content;
        this.user_id = user_id;
        this.msg_type = msg_type;
    }
    
    public MessegesSideModel() {
    }
    public int getMsg_type() {
        return msg_type;
    }
    public void setMsg_type(int msg_type) {
        this.msg_type = msg_type;
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
