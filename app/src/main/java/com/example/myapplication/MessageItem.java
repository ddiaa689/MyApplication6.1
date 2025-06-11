package com.example.myapplication;

import java.io.Serializable;

public class MessageItem implements Serializable {
    public int avatarResId;
    public String title;
    public String content;
    public String time;
    public int unreadCount;

    public MessageItem(int avatarResId, String title, String content, String time, int unreadCount) {
        this.avatarResId = avatarResId;
        this.title = title;
        this.content = content;
        this.time = time;
        this.unreadCount = unreadCount;
    }

    // 可选：为点击传递对象方便，实现Serializable接口（已加到类声明）
    
}