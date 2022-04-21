package uk.gov.hmcts.reform.rsecheck.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.hmcts.reform.rpts.controllers.AddressController;
import uk.gov.hmcts.reform.rpts.models.NsplAddress;
import uk.gov.hmcts.reform.rpts.services.NsplService;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
@ContextConfiguration(classes = AddressController.class)
class AddressControllerTest {

    private static final String BASE_URL = "/v1/search/address/";

    @MockBean
    private NsplService nsplService;

    @Autowired
    private transient MockMvc mockMvc;

    @Test
    void shouldFindCourtByQuery() throws Exception {

        final Path path = Paths.get("src/test/resources/nspl_address_example.json");
        final String expectedJson = new String(readAllBytes(path));

        final NsplAddress nsplAddress = new ObjectMapper().readValue(path.toFile(), NsplAddress.class);
        final String query = "PL51AA";

        when(nsplService.getAddressInfo(query)).thenReturn(nsplAddress);

        mockMvc.perform(get(BASE_URL + query))
            .andExpect(status().isOk())
            .andExpect(content().json(expectedJson))
            .andReturn();
    }
}
