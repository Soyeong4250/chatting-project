package com.example.chatting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessageList = new ArrayList<>();

    @AllArgsConstructor
    @Getter
    public static class writeMessageResponse {
        private final long id;
    }

    // java17버전에서는 위와 같은 코드를 레코드로 인식하여 다음과 같이 작성
    /*public record WriteMessageResponse(long id){
    }*/

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<ChatMessage> writeMessage() {
        ChatMessage message = new ChatMessage("홍길동", "안녕하세요.");

        chatMessageList.add(message);

        return new RsData<>("S-1",
                "메세지가 작성되었습니다.",
                message);
    }

    @GetMapping("/message")
    @ResponseBody
    public RsData<List <ChatMessage>> message() {
        return new RsData<>(
                "S-1",
                "Success",
                chatMessageList
        );
    }
}
