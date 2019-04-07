package first.hibernate.project.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jackson2ObjectMapperBuilder -> {
            JavaTimeModule module = new JavaTimeModule();

            jackson2ObjectMapperBuilder
                    .featuresToDisable(
                            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                            SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .featuresToEnable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
                    .findModulesViaServiceLoader(true)
                    .modulesToInstall(module);
        };
    }
}
