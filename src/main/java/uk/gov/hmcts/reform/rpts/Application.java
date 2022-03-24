package uk.gov.hmcts.reform.rpts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings({"HideUtilityClassConstructor", "PMD"})
public class Application {

    public static void main(final String[] args) {

        System.out.println(System.getenv("POSTGRES_HOST"));
        System.out.println(System.getenv("POSTGRES_USERNAME"));

        SpringApplication.run(Application.class, args);
    }
}
