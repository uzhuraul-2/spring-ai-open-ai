package uladzislau.zhurauliou.spring_ai_open_ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import uladzislau.zhurauliou.spring_ai_open_ai.dto.Answer;
import uladzislau.zhurauliou.spring_ai_open_ai.dto.Question;

import java.util.List;

@Service
public class OpenAIService {

    private final ChatClient client;

    public OpenAIService(ChatClient.Builder builder) {
        this.client = builder
                .build();
    }

    public Answer getAnswerWithPrompt(Question question) {
        Message message = new UserMessage(question.question());
        Message systemMessage = new SystemMessage("Respond in 1 word. Wrap response with <response> tag");
        Prompt prompt = new Prompt(List.of(message, systemMessage));
        ChatClient.CallPromptResponseSpec call = client.prompt(prompt)
                .call();
        ChatResponse chatResponse = call.chatResponse();
        return new Answer(chatResponse.getResult().getOutput().getContent());
    }

    public Answer getAnswer(Question question) {
        ChatClient.CallResponseSpec call = client.prompt()
                .user(question.question())
                .system("Respond in 3 words")
                .call();
        String content = call.content();
        return new Answer(content);
    }

}
