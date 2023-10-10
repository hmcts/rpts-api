package uk.gov.hmcts.reform.rpts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import uk.gov.hmcts.reform.rpts.os.OsClient;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = OsClient.class)
@SuppressWarnings({"HideUtilityClassConstructor", "PMD"})
@ComponentScan(basePackages = {"uk.gov.hmcts.reform.sendletter", "uk.gov.hmcts.reform.rpts"})
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
