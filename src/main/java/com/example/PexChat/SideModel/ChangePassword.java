package com.example.PexChat.SideModel;

public class ChangePassword {
    public String oldPass;
    public String newPass;

    public ChangePassword() {
    }

    public ChangePassword(String oldPass, String newPass) {
        this.oldPass = oldPass;
        this.newPass = newPass;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    } 
    
}
