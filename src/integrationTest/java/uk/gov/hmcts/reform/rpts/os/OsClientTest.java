package uk.gov.hmcts.reform.rpts.os;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
 class OsClientTest {

    @Autowired
    private OsClient osClient;

    @Test
    void shouldReturnPostcodeData() throws JacksonException {
        // SW1A 1AA is the postcode for Buckingham Palace, London
        final  OsResult osResult = osClient.getPostcodeData("SW1A 1AA");
        assertThat(osResult.getResults()).isNotEmpty();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode newNode = mapper.readTree(String.valueOf(osResult.getResults()));
        assertThat(newNode.findPath("DPA").path("POSTCODE").textValue()).isEqualTo("SW1A 1AA");
        assertThat(newNode.findPath("DPA").path("POST_TOWN").textValue()).isEqualTo("LONDON");
    }

    @Test
    void shouldReturnNullPostcodeDataForPostcodeNotExist() {
        final  OsResult osResult = osClient.getPostcodeData("aa1 2aa");
        assertThat(osResult.getResults()).isNull();
    }

    @Test
    void shouldThrowBadRequestExceptionForWrongSyntaxPostcode() {

        assertThatThrownBy(() -> osClient.getPostcodeData("aa1 aa"))
            .isInstanceOf(feign.FeignException.BadRequest.class)
            .hasMessageContaining("Requested postcode must contain a minimum of the sector plus 1 digit of the "
                                      + "district e.g. SO1. Requested postcode was aa1aa");

    }

    @Test
    void shouldThrowBadRequestExceptionForEmptyPostcode() {

        assertThatThrownBy(() -> osClient.getPostcodeData(""))
            .isInstanceOf(feign.FeignException.BadRequest.class)
            .hasMessageContaining("Parameter postcode cannot be empty.");

    }

}
