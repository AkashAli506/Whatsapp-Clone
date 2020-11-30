package com.akashali.i170019_i170282;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Chat {
    private Bitmap img;
    private String sender;
    private String messageId;
    private String message;
    ArrayList<String> mUriList;

    public Chat(Bitmap img,String sender, String messageId, String message,ArrayList<String> mUriList) {
        this.img = img;
        this.sender = sender;
        this.messageId = messageId;
        this.message = message;
        this.mUriList = mUriList;
    }

    public ArrayList<String> getmUriList() {
        return mUriList;
    }

    public void setmUriList(ArrayList<String> mUriList) {
        this.mUriList = mUriList;
    }

    public Chat(String sender, String messageId, String message) {
        this.sender = sender;
        this.messageId = messageId;
        this.message = message;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}