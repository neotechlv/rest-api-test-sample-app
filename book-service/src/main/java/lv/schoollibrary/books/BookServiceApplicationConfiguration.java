package lv.schoollibrary.books;

import lv.schoollibrary.books.domain.EntityMapper;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class BookServiceApplicationConfiguration {

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    EntityMapper entityMapper() {
        return Mappers.getMapper(EntityMapper.class);
    }

}
