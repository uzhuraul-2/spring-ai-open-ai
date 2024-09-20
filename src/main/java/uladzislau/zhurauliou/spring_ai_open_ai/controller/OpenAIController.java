package uladzislau.zhurauliou.spring_ai_open_ai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uladzislau.zhurauliou.spring_ai_open_ai.dto.Answer;
import uladzislau.zhurauliou.spring_ai_open_ai.service.OpenAIService;

@RestController
@RequiredArgsConstructor
public class OpenAIController {

    private final OpenAIService openAIService;

    @GetMapping("/actor/films")
    public Answer getActorFilms(@RequestParam String actor) {
        return openAIService.getActorFilms(actor);
    }

}
