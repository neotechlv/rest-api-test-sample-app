package lv.schoollibrary.schoolkids.config;

import org.mapstruct.factory.Mappers;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Clock;

import lv.schoollibrary.schoolkids.dtos.EntityMapper;

@Configuration
public class SchoolKidServiceApplicationConfiguration {

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public RestTemplate eklaseRestTemplate(RestTemplateBuilder builder) {
        return builder
                .errorHandler(new NoOpResponseErrorHandler())
                .build();
    }

    @Bean
    EntityMapper entityMapper() {
        return Mappers.getMapper(EntityMapper.class);
    }

    private static class NoOpResponseErrorHandler extends DefaultResponseErrorHandler {
        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
        }
    }

}
