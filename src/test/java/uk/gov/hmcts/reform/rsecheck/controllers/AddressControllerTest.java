package uk.gov.hmcts.reform.rsecheck.controllers;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;
import uk.gov.hmcts.reform.rpts.Application;
import uk.gov.hmcts.reform.rpts.controllers.AddressController;
import uk.gov.hmcts.reform.rpts.exceptions.NotFoundException;
import uk.gov.hmcts.reform.rpts.models.NsplAddress;
import uk.gov.hmcts.reform.rpts.services.NsplService;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
@ContextConfiguration(classes = Application.class)
@SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
class AddressControllerTest {

    private static final String BASE_URL = "/v1/search/address/";

    @MockitoBean
    private NsplService nsplService;

    @Autowired
    private transient MockMvc mockMvc;

    @Test
    void shouldFindCourtByQuery() throws Exception {

        final Path path = Path.of("src/test/resources/nspl_address_example.json");
        final NsplAddress nsplAddress = new ObjectMapper().readValue(path.toFile(), NsplAddress.class);
        final String query = "PL51AA";

        when(nsplService.getAddressInfo(query)).thenReturn(nsplAddress);

        mockMvc.perform(get(BASE_URL + query))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.postcode").value(nsplAddress.getPostcode()))
            .andExpect(jsonPath("$.fourCharLaCode").value(nsplAddress.getFourCharLaCode()))
            .andExpect(jsonPath("$.nineCharLaCode").value(nsplAddress.getNineCharLaCode()))
            .andExpect(jsonPath("$.addresses.length()").value(nsplAddress.getAddresses().size()))
            .andReturn();
    }

    @Test
    void shouldReturnInvalidPostCodeError() throws Exception {
        try {
            mockMvc.perform(get(BASE_URL + "abc123")).andReturn();
        } catch (Exception e) {
            assertThrows(
                ConstraintViolationException.class, () -> {
                    throw e.getCause();
                }
            );
            assertThat(e.getMessage())
                .containsPattern("getAddress.postcode: Provided postcode is not valid");
        }
        verifyNoInteractions(nsplService);
    }

    @Test
    void shouldUseGlobalExceptionHandler() throws Exception {

        final String query = "PL51AA";

        when(nsplService.getAddressInfo(query)).thenThrow(new NotFoundException(query));

        try {
            mockMvc.perform(get(BASE_URL + query)).andExpect(status().isNotFound());
        } catch (Exception e) {
            assertThrows(
                NotFoundException.class, () -> {
                    throw e.getCause();
                }
            );
            assertThat(e.getMessage())
                .containsPattern("uk.gov.hmcts.reform.rpts.exceptions.NotFoundException: Not found: PL51AA");
        }
    }
}
