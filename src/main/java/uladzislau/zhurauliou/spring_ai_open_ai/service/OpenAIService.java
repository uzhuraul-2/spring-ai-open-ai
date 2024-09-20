package uladzislau.zhurauliou.spring_ai_open_ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import uladzislau.zhurauliou.spring_ai_open_ai.dto.Answer;
import uladzislau.zhurauliou.spring_ai_open_ai.dto.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAIService {

    @Value("classpath:prompts/system-message.st")
    private Resource systemResource;

    private final ChatClient client;

    public OpenAIService(ChatClient.Builder builder) {
        this.client = builder
                .build();
    }

    public Answer getAnswerWithPrompt(Question question) {
        Message message = new UserMessage(question.question());

        Map<String, Object> model = new HashMap<>();
        model.put("name", "Alice");
        model.put("voice", "wizard");
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemResource);
        Message systemPromptTemplateMessage = systemPromptTemplate.createMessage(model);

        Prompt prompt = new Prompt(List.of(message, systemPromptTemplateMessage));
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
