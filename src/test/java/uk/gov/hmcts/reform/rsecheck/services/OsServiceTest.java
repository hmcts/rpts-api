package uk.gov.hmcts.reform.rsecheck.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.hmcts.reform.rpts.os.OsClient;
import uk.gov.hmcts.reform.rpts.os.OsResult;
import uk.gov.hmcts.reform.rpts.services.OsService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OsService.class)
class OsServiceTest {

    private static final String RESPONSE_MESSAGE = "message";

    @MockBean
    private OsClient osClient;

    @Autowired
    private OsService osService;

    @Test
    void shouldReturnOptionalForValidPostcode() throws IOException {
        final String postcode = "OX1 1RZ";
        final Path path = Paths.get("src/test/resources/os_address_example.json");
        final OsResult osResult = new ObjectMapper().readValue(path.toFile(), OsResult.class);
        when(osClient.getPostcodeData(postcode)).thenReturn(osResult);
        assertThat(osService.getOsAddressData(postcode))
            .isPresent()
            .isEqualTo(Optional.of(osResult));
    }

    @Test
    void shouldReturnEmptyOptionalForBlankPostcode() {
        final String postcode = "";
        assertThat(osService.getOsAddressData(postcode)).isEmpty();
    }

    @Test
    void shouldReturnOptionalEmptyIfFeignExceptionIsThrown() {
        final String postcode = "OX1";
        final FeignException feignException = mock(FeignException.class);
        when(osClient.getPostcodeData(postcode)).thenThrow(feignException);
        when(feignException.status()).thenReturn(400);
        when(feignException.getMessage()).thenReturn(RESPONSE_MESSAGE);
        final Optional<OsResult> result = osService.getOsAddressData(postcode);
        assertThat(result).isNotPresent();
    }
}
