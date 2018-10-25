package lv.schoollibrary.orders;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(OrderServiceApplication.class)
                .listeners(new ApplicationPidFileWriter())
                .run(args);
    }

}
