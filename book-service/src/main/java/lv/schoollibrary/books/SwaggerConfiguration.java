package lv.schoollibrary.books;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ConditionalOnWebApplication
public class SwaggerConfiguration {

    @ConditionalOnProperty(prefix = "swagger", name = "enabled")
    @EnableSwagger2
    public static class SwaggerUiConfiguration {

        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .useDefaultResponseMessages(false)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage(BookServiceApplication.class.getPackage().getName()))
                    .paths(PathSelectors.any())
                    .build();
        }

    }

    @Controller
    @ConditionalOnProperty(prefix = "swagger", name = "enabled", havingValue = "false")
    public static class SwaggerController {

        //Since you can't disable swagger UI completely via properties, we're short circuiting manually
        @RequestMapping("**/swagger-ui.html")
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public String shortCircuitSwagger() {
            return "Not Found";
        }

    }

}
