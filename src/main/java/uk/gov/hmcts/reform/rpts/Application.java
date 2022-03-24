package uk.gov.hmcts.reform.rpts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings("HideUtilityClassConstructor") // Spring needs a constructor, its not a utility class
public class Application {

    public static void main(final String[] args) {
        System.out.println(System.getenv("POSTGRES_HOST"));
        System.out.println(System.getenv("POSTGRES_USERNAME"));
        System.out.println(System.getenv("POSTGRES_DATABASE"));
        System.out.println(System.getenv("POSTGRES_PORT"));

        SpringApplication.run(Application.class, args);
    }
}
