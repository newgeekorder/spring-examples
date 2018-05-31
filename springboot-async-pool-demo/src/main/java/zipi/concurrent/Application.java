package zipi.concurrent;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main application configuration class
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "zipi.concurrent")
public class Application {


    public static void main(final String... args) {

        new SpringApplicationBuilder(Application.class).build().run(args);

    }

}
