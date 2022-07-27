package com.example.PexChat.Model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;


@Embeddable
public class MessegesKey implements Serializable {
    
    @Column(name = "room_id")
    @Type(type ="uuid-char")
    UUID room_id;
    @Column(name = "user_id")
    @Type(type ="uuid-char")

    UUID user_id;
    public UUID getRoom_id() {
        return room_id;
    }
    public void setRoom_id(UUID room_id) {
        this.room_id = room_id;
    }
    public UUID getUser_id() {
        return user_id;
    }
    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }
    public MessegesKey(UUID room_id, UUID user_id) {
        this.room_id = room_id;
        this.user_id = user_id;
    }

    

}
