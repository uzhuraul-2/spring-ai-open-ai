package uladzislau.zhurauliou.spring_ai_open_ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import uladzislau.zhurauliou.spring_ai_open_ai.dto.Answer;
import uladzislau.zhurauliou.spring_ai_open_ai.dto.Question;

@Service
public class OpenAIService {

    private final ChatClient client;

    public OpenAIService(ChatClient.Builder builder) {
        this.client = builder
                .build();
    }

    public Answer getAnswer(Question question) {
        ChatClient.CallResponseSpec call = client.prompt()
                .user(question.question())
                .call();
        String content = call.content();
        return new Answer(content);
    }

}
