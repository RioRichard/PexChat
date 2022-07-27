package com.example.PexChat.Model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Messages")
public class Messenges implements Serializable {
    @EmbeddedId
    MessegesKey key;

    @OneToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    Users user;

    @OneToOne
    @MapsId("room_id")
    @JoinColumn(name = "room_id")
    Room room;

    public MessegesKey getKey() {
        return key;
    }

    public void setKey(MessegesKey key) {
        this.key = key;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Messenges() {
    }

    public Messenges(Users user, Room room) {
        this.key = new MessegesKey(user.getUser_id(), room.getRoom_id());
        this.user = user;
        this.room = room;
    }
    
    

    
}
