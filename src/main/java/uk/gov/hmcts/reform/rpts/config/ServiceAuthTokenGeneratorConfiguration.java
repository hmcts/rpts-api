package uk.gov.hmcts.reform.rpts.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;

@Configuration
@Lazy
@Slf4j
//@EnableFeignClients(basePackageClasses = ServiceAuthorisationApi.class)
public class ServiceAuthTokenGeneratorConfiguration {

    @Bean
    public AuthTokenGenerator serviceAuthTokenGenerator(
        @Value("${idam.s2s-auth.totp_secret}") final String secret,
        @Value("${idam.s2s-auth.microservice}") final String microService) {
        // final ServiceAuthorisationApi serviceAuthorisationApi) {
        log.info("serviceAuthTokenGenerator: {}", secret.substring(0, 4));

        //return AuthTokenGeneratorFactory.createDefaultGenerator(secret, microService, serviceAuthorisationApi);
        return new AuthTokenGenerator() {
            @Override
            public String generate() {
                return "FinRem";
            }
        };
    }
}
