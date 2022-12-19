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
        this(ChatMessageIdGenerator.genNextId(), LocalDateTime.now(), author, content);
    }

}
class ChatMessageIdGenerator {
    public static long id = 0;

    public static long genNextId() {
        return ++id;
    }
}
