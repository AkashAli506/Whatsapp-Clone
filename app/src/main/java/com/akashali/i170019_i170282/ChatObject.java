package com.akashali.i170019_i170282;

import android.graphics.Bitmap;

public class ChatObject {
    private String key;
    private String phone;
    private Bitmap image;
    private String name;
    private String message;
    private String time;
    private boolean isOnline;
    private String messageCount;

    public ChatObject(Bitmap image, String name, String message, String time, boolean isOnline, String messageCount, String key,String phone) {
        this.image = image;
        this.name = name;
        this.message = message;
        this.time = time;
        this.isOnline = isOnline;
        this.messageCount = messageCount;
        this.key = key;
        this.phone = phone;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ChatObject(String key) {
        this.key = key;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isOnline() {
        return isOnline;
    }
    public void SetOnline() {
        isOnline = true;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }
}
