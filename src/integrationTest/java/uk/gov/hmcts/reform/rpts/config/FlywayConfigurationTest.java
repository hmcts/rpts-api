package uk.gov.hmcts.reform.rpts.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(properties = {"dbMigration.runOnStartup=false"})
@ContextConfiguration(classes = FlywayConfiguration.class)
class FlywayConfigurationTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void shouldAddFlywayConfigurationBeanToApplicationContext() {
        assertThat(applicationContext.containsBean("flywayConfiguration")).isTrue();
    }

    @Test
    void shouldReturnFlywayNoOpStrategy() {
        final FlywayConfiguration flywayConfiguration = (FlywayConfiguration) (applicationContext.getBean(
            "flywayConfiguration"));
        assertThat(flywayConfiguration.flywayMigrationStrategy() instanceof FlywayNoOpStrategy).isTrue();
    }
}
