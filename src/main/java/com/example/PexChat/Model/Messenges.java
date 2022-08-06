package com.example.PexChat.Model;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "Messages")
public class Messenges implements Serializable {

    public static final int MESSAGE = 1;
    public static final int IMAGE = 2;
    public static final int JOIN = 3;
    @Id
    @Type(type = "uuid-char")
    UUID message_id;

    @OneToOne

    @JoinColumn(name = "user_id")
    Users user;

    @OneToOne

    @JoinColumn(name = "room_id")
    Room room;

    String content;
    Date date_send;
    int data_type;
    public Messenges() {
    }
    public Messenges(UUID message_id, Users user, Room room, String content, Date date_created, int data_type) {
        this.message_id = message_id;
        this.user = user;
        this.room = room;
        this.content = content;
        this.date_send = date_created;
        this.data_type = data_type;
    }
    public UUID getMessage_id() {
        return message_id;
    }
    public void setMessage_id(UUID message_id) {
        this.message_id = message_id;
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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getDate_created() {
        return date_send;
    }
    public void setDate_created(Date date_created) {
        this.date_send = date_created;
    }
    public int getData_type() {
        return data_type;
    }
    public void setData_type(int data_type) {
        this.data_type = data_type;
    }
    
}
