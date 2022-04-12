package uk.gov.hmcts.reform.rpts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import uk.gov.hmcts.reform.rpts.os.OsClient;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = OsClient.class)
@SuppressWarnings({"HideUtilityClassConstructor", "PMD"})
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
