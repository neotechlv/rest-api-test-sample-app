package lv.schoollibrary.schoolkids;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class SchoolKidServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SchoolKidServiceApplication.class)
                .listeners(new ApplicationPidFileWriter())
                .run(args);
    }

}
