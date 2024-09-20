package uladzislau.zhurauliou.spring_ai_open_ai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uladzislau.zhurauliou.spring_ai_open_ai.dto.Answer;
import uladzislau.zhurauliou.spring_ai_open_ai.dto.Question;
import uladzislau.zhurauliou.spring_ai_open_ai.service.OpenAIService;

@RestController
@RequiredArgsConstructor
public class OpenAIController {

    private final OpenAIService openAIService;

    @PostMapping("/ask")
    public Answer getAnswer(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }

}
