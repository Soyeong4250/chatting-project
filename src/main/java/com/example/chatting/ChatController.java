package com.example.chatting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@Slf4j
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessageList = new ArrayList<>();

    @AllArgsConstructor
    @Getter
    public static class WriteMessageResponse {
        private final long id;
    }

    // java17버전에서는 위와 같은 코드를 레코드로 인식하여 다음과 같이 작성
    /*public record WriteMessageResponse(long id){
    }*/

    @AllArgsConstructor
    @Getter
    public static class WriteMessageRequest {
        private final String author;
        private final String content;
    }
    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<WriteMessageResponse> writeMessage(@RequestBody WriteMessageRequest req) {
        ChatMessage message = new ChatMessage(req.author, req.content);

        chatMessageList.add(message);

        return new RsData<>("S-1",
                "메세지가 작성되었습니다.",
                new WriteMessageResponse(message.getId()));
    }

    @AllArgsConstructor
    @Getter
    public static class MessagesRequest {
        private final Long fromId;
    }

    @AllArgsConstructor
    @Getter
    public static class MessagesResponse {
        private final List<ChatMessage> chatMessageList;
        private final int size;
    }

    @GetMapping("/message")
    @ResponseBody
    public RsData<MessagesResponse> message(MessagesRequest req) {
        log.debug("req : {}",  req);

        List<ChatMessage> messages = chatMessageList;

        if(req.fromId != null) {
            int idx = IntStream.range(0, messages.size())
                    .filter(i -> chatMessageList.get(i).getId() == req.fromId)
                    .findFirst()
                    .orElse(-1);

            if(idx != -1) {
                messages = messages.subList(idx + 1, messages.size());
            }
        }

        return new RsData<>(
                "S-1",
                "Success",
                new MessagesResponse(messages, messages.size()));
    }
}
