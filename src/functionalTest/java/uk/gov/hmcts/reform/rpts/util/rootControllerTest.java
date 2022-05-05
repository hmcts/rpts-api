package uk.gov.hmcts.reform.rpts.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.hmcts.reform.rpts.util.FunctionalTestBase;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;

@ExtendWith({SpringExtension.class})

public class rootControllerTest extends FunctionalTestBase {

    @Test
    public void shouldDisplayWelcomeText() {
        final var response = doGetRequest("localhost:4000/");
        assertThat(response.statusCode()).isEqualTo(OK.value());

    }

}
