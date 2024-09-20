package uladzislau.zhurauliou.spring_ai_open_ai.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.AdvisedRequest;
import org.springframework.ai.chat.client.RequestResponseAdvisor;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.model.ChatResponse;

import java.util.Map;

@Slf4j
public class LoggerAdvisor implements RequestResponseAdvisor {

    @Override
    public AdvisedRequest adviseRequest(AdvisedRequest request, Map<String, Object> context) {
        String model = request.chatOptions().getModel();
        String userText = request.userText();
        String systemText = request.systemText();

        log.info(String.format("Request to %s model.", model));
        log.info(String.format("User text: %s.", userText));
        log.info(String.format("System text: %s", systemText));
        return request;
    }

    @Override
    public ChatResponse adviseResponse(ChatResponse response, Map<String, Object> context) {
        ChatResponseMetadata metadata = response.getMetadata();

        log.info(String.format("Response metadata: %s", metadata));
        return response;
    }
}
