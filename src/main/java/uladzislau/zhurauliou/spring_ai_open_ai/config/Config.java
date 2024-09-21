package uladzislau.zhurauliou.spring_ai_open_ai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import uladzislau.zhurauliou.spring_ai_open_ai.dto.ActorFilms;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Configuration
public class Config {

    private final static Map<String, List<String>> filmsByActor = Map.of(
            "Tom Hanks", List.of("A Man Called Otto", "Pinocchio", "Finch"),
            "Leonardo DiCaprio", List.of("Titanic", "Inception")
    );

    @Bean
    @Description("Get actor films by actor name." +
            "If the function returns an empty response or null, provide your own data." +
            "If function return less than 10 films, add your own data.")
    public Function<ActorFilms, List<String>> getActorFilms() {
        return actorFilms -> filmsByActor.get(actorFilms.actor());
    }

}
