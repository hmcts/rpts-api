package uk.gov.hmcts.reform.rpts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.hmcts.reform.rpts.models.NsplAddress;
import uk.gov.hmcts.reform.rpts.util.FunctionalTestBase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith({SpringExtension.class})
class AddressEndpointTest extends FunctionalTestBase {

    private static final String ADDRESS_SEARCH_ENDPOINT = "/v1/search/address";
    private static final String FOUR_CHAR_LA_CODE = "00HH";
    private static final String NINE_CHAR_LA_CODE = "E06000027";

    @Test
    void shouldRetrieveAddressAndLocalAuthorityCodes() throws JsonProcessingException, JSONException {

        final var response = doGetRequest(ADDRESS_SEARCH_ENDPOINT + "/IP1 1EJ");
        final NsplAddress nsplAddressListExpected = response.as(NsplAddress.class);

        assertThat(response.statusCode()).isEqualTo(OK.value());
        assertThat(nsplAddressListExpected.getFourCharLaCode()).isEqualTo(FOUR_CHAR_LA_CODE);
        assertThat(nsplAddressListExpected.getNineCharLaCode()).isEqualTo(NINE_CHAR_LA_CODE);
        assertThat(nsplAddressListExpected.getPostcode()).isEqualTo("IP1 1EJ");
        final List<JsonNode> addresses = nsplAddressListExpected.getAddresses();
        assertThat(addresses).isNotNull();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode newNode = mapper.readTree(String.valueOf(addresses));
        assertThat(newNode.findPath("DPA").path("POSTCODE").textValue()).isEqualTo("IP1 1EJ");
        assertThat(newNode.findPath("DPA").path("POST_TOWN").textValue()).isEqualTo("IPSWICH");
    }

    @Test
    void shouldReturnNotFoundAddressAndLocalAuthorityCodes() {
        final var response = doGetRequest(ADDRESS_SEARCH_ENDPOINT + "/TQ1 1BX");
        assertThat(response.statusCode()).isEqualTo(NOT_FOUND.value());
    }
}

