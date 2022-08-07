package com.example.PexChat.SideModel;


import com.example.PexChat.Model.Room;

public class SubscribeSideModel {
    int messengesType;
    Room room;
    public int getMessenges() {
        return messengesType;
    }
    public void setMessengesType(int messenges) {
        this.messengesType = messenges;
    }
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public SubscribeSideModel() {
    }
    
}
