package lv.schoollibrary.orders;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

import lv.schoollibrary.orders.domain.EntityMapper;

@Configuration
public class OrderServiceApplicationConfiguration {

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    EntityMapper entityMapper() {
        return Mappers.getMapper(EntityMapper.class);
    }

}
