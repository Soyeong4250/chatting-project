package com.example.chatting;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ChatMessage {
    private long id;
    private LocalDateTime createDate;
    private String author;
    private String content;

    public ChatMessage(String author, String content) {
        this(1, LocalDateTime.now(), author, content);
    }
}
