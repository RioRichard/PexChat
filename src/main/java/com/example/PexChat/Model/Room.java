package com.example.PexChat.Model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Room implements Serializable{
    

    @Id
    @Type(type = "uuid-char")
    UUID room_id ;
    
    String room_name;
    Date date_created;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    List<Messenges> messages = new ArrayList<>();
    
    public Room() {
    }
    
    
    

    public Room(UUID room_id, String room_name, Date date_created) {
        this.room_id = room_id;
        this.room_name = room_name;
        this.date_created = date_created;
    }




    public Room(UUID room_id, String room_name, Date date_created, List<Messenges> messages) {
        this.room_id = room_id;
        this.room_name = room_name;
        this.date_created = date_created;
        this.messages = messages;
    }



    public UUID getRoom_id() {
        return room_id;
    }
    public void setRoom_id(UUID room_id) {
        this.room_id = room_id;
    }
    public String getRoom_name() {
        return room_name;
    }
    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }
    public Date getDate_created() {
        return date_created;
    }
    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public List<Messenges> getMessages() {
        return messages;
    }

    public void setMessages(List<Messenges> messages) {
        this.messages = messages;
    }
}
