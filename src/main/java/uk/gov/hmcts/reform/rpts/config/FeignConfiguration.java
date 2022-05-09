package uk.gov.hmcts.reform.rpts.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Value("${os.key}")
    private String key;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.header("key", key);
    }
}
