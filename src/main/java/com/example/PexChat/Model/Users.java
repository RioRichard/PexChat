package com.example.PexChat.Model;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Users {
    @Id
    UUID user_id;
    String username;
    
    String backup_code;
    Date date_created;
    String avartar;

    public Users() {
    }

    public Users(UUID user_id, String username, byte[] password, String backup_code, Date date_created, String avartar) {
        this.user_id = user_id;
        this.username = username;
        
        this.backup_code = backup_code;
        this.date_created = date_created;
        this.avartar = avartar;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // public byte[] getPassword() {
    //     return password;
    // }

    // public void setPassword(byte[] password) {
    //     this.password = password;
    // }

    public String getBackup_code() {
        return backup_code;
    }

    public void setBackup_code(String backup_code) {
        this.backup_code = backup_code;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

}
