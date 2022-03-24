package uk.gov.hmcts.reform.rpts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings({"HideUtilityClassConstructor", "PMD"})
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
