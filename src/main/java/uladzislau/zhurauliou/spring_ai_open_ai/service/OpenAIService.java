package uladzislau.zhurauliou.spring_ai_open_ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import uladzislau.zhurauliou.spring_ai_open_ai.dto.ActorFilms;
import uladzislau.zhurauliou.spring_ai_open_ai.dto.Answer;

@Service
public class OpenAIService {

    @Value("classpath:prompts/system-message.st")
    private Resource systemResource;

    private final ChatClient client;

    public OpenAIService(ChatClient.Builder builder) {
        this.client = builder
                .defaultAdvisors(new LoggerAdvisor())
                .defaultOptions(OpenAiChatOptions.builder()
                        .withModel(OpenAiApi.ChatModel.GPT_3_5_TURBO)
                        .build())
                .build();
    }

    public Answer getActorFilms(String actor) {
        BeanOutputConverter<ActorFilms> beanOutputConverter = new BeanOutputConverter<>(ActorFilms.class);
        ActorFilms actorFilms = client.prompt()
                .user("Generate the filmography for an actor: {actor}")
                .user(promptUserSpec -> promptUserSpec.param("actor", actor))
                .call()
                .entity(beanOutputConverter);
        return new Answer(actorFilms);
    }

}
