package uk.gov.hmcts.reform.rpts.config;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FeignConfiguration {

    @Value("${os.key}")
    private String key;

    @Bean
    public RequestInterceptor requestInterceptor() {
        log.info("testy testy test");
        log.info(key);
        return template -> template.header("key", key);
    }
}
