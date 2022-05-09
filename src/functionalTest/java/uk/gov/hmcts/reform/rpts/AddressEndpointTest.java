package uk.gov.hmcts.reform.rpts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.hmcts.reform.rpts.util.FunctionalTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith({SpringExtension.class})
class AddressEndpointTest extends FunctionalTestBase {

    private static final String ADDRESS_SEARCH_ENDPOINT = "/v1/search/address";

    @Test
    void shouldRetrieveAddressAndLocalAuthorityCodes() {
        final var response = doGetRequest(ADDRESS_SEARCH_ENDPOINT + "/TQ1 1BS");
        assertThat(response.statusCode()).isEqualTo(OK.value());
    }

    @Test
    void shouldReturnNotFoundAddressAndLocalAuthorityCodes() {
        final var response = doGetRequest(ADDRESS_SEARCH_ENDPOINT + "/TQ1 1BX");
        assertThat(response.statusCode()).isEqualTo(NOT_FOUND.value());
    }

}
