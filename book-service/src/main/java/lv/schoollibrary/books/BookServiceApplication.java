package lv.schoollibrary.books;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class BookServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BookServiceApplication.class)
                .listeners(new ApplicationPidFileWriter())
                .run(args);
    }

}
