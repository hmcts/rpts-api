package uk.gov.hmcts.reform.rpts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.hmcts.reform.rpts.util.FunctionalTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import uk.gov.hmcts.reform.rpts.models.NsplAddress;

@ExtendWith({SpringExtension.class})
class AddressEndpointTest extends FunctionalTestBase {

    private static final String ADDRESS_SEARCH_ENDPOINT = "/v1/search/address";
    private static final String FOUR_CHAR_LA_CODE = "00HH";
    private static final String NINE_CHAR_LA_CODE = "E06000027";

    @Test
    void shouldRetrieveAddressAndLocalAuthorityCodes() {

        final var response = doGetRequest(ADDRESS_SEARCH_ENDPOINT + "/TQ1 1BS");
        final NsplAddress NsplAddressListExpected = response.as(NsplAddress.class);

        assertThat(response.statusCode()).isEqualTo(OK.value());
        assertThat(NsplAddressListExpected.getFourCharLaCode()).isEqualTo(FOUR_CHAR_LA_CODE);
        assertThat(NsplAddressListExpected.getNineCharLaCode()).isEqualTo(NINE_CHAR_LA_CODE);

        // TODO: add postcode check, and addresses list check
    }

    @Test
    void shouldReturnNotFoundAddressAndLocalAuthorityCodes() {
        final var response = doGetRequest(ADDRESS_SEARCH_ENDPOINT + "/TQ1 1BX");
        assertThat(response.statusCode()).isEqualTo(NOT_FOUND.value());
    }
}

