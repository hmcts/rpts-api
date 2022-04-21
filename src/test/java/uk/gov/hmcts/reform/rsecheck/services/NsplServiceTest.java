package uk.gov.hmcts.reform.rsecheck.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.hmcts.reform.rpts.entities.Nspl;
import uk.gov.hmcts.reform.rpts.entities.NsplHistory;
import uk.gov.hmcts.reform.rpts.exceptions.NotFoundException;
import uk.gov.hmcts.reform.rpts.models.NsplAddress;
import uk.gov.hmcts.reform.rpts.os.OsResult;
import uk.gov.hmcts.reform.rpts.repositories.NsplHistoryRepo;
import uk.gov.hmcts.reform.rpts.repositories.NsplRepo;
import uk.gov.hmcts.reform.rpts.services.NsplService;
import uk.gov.hmcts.reform.rpts.services.OsService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NsplService.class)
public class NsplServiceTest {

    @MockBean
    private NsplRepo nsplRepo;
    @MockBean
    private NsplHistoryRepo nsplHistoryRepo;
    @MockBean
    private OsService osService;

    @Autowired
    private NsplService nsplService;

    private static final Nspl nspl = new Nspl();
    private static final NsplHistory nsplHistory = new NsplHistory();
    private static final String postcode = "PL5 1AA";
    private static Path path;
    private static Optional<OsResult> osResult;

    @BeforeAll
    static void beforeAll() throws IOException {
        nspl.setLaua("a laua");
        nspl.setPcd(postcode);
        nsplHistory.setGeogcdo("a geogcdo");

        path = Paths.get("src/test/resources/os_address_example.json");
        osResult = Optional.of(new ObjectMapper().readValue(path.toFile(), OsResult.class));
    }

    @Test
    void shouldReturnResultsWithPcdIgnoreCaseSearch() {
        when(nsplRepo.findAllByPcdIgnoreCase(postcode)).thenReturn(Optional.of(nspl));
        when(nsplHistoryRepo.findAllByGeogcdIgnoreCaseAndStatus(nspl.getLaua(), "live")).thenReturn(nsplHistory);
        when(osService.getOsAddressData(postcode)).thenReturn(osResult);

        NsplAddress nsplAddress = nsplService.getAddressInfo(postcode);

        assertEquals(nsplAddress.getPostcode(), postcode);
        assertEquals(nsplAddress.getFourCharLaCode(), "a geogcdo");
        assertEquals(nsplAddress.getNineCharLaCode(), "a laua");
        assertEquals(nsplAddress.getAddresses(), osResult.get().getResults());

        verify(nsplRepo, atLeastOnce()).findAllByPcdIgnoreCase(postcode);
        verify(nsplRepo, never()).findAllByPcd2IgnoreCase(postcode);
        verify(nsplRepo, never()).findAllByPostcodeTrimmed(postcode);
        verify(nsplHistoryRepo, atLeastOnce()).findAllByGeogcdIgnoreCaseAndStatus(nspl.getLaua(), "live");
        verify(osService, atLeastOnce()).getOsAddressData(postcode);
    }

    @Test
    void shouldReturnResultsWithPcd2IgnoreCaseSearch() {
        when(nsplRepo.findAllByPcd2IgnoreCase(postcode)).thenReturn(Optional.of(nspl));
        when(nsplHistoryRepo.findAllByGeogcdIgnoreCaseAndStatus(nspl.getLaua(), "live")).thenReturn(nsplHistory);
        when(osService.getOsAddressData(postcode)).thenReturn(osResult);

        NsplAddress nsplAddress = nsplService.getAddressInfo(postcode);

        assertEquals(nsplAddress.getPostcode(), postcode);
        assertEquals(nsplAddress.getFourCharLaCode(), "a geogcdo");
        assertEquals(nsplAddress.getNineCharLaCode(), "a laua");
        assertEquals(nsplAddress.getAddresses(), osResult.get().getResults());

        verify(nsplRepo, atLeastOnce()).findAllByPcdIgnoreCase(postcode);
        verify(nsplRepo, atLeastOnce()).findAllByPcd2IgnoreCase(postcode);
        verify(nsplRepo, never()).findAllByPostcodeTrimmed(postcode);
        verify(nsplHistoryRepo, atLeastOnce()).findAllByGeogcdIgnoreCaseAndStatus(nspl.getLaua(), "live");
        verify(osService, atLeastOnce()).getOsAddressData(postcode);
    }

    @Test
    void shouldReturnResultsWithPcdTrimmedSearch() {
        when(nsplRepo.findAllByPostcodeTrimmed(postcode)).thenReturn(Optional.of(nspl));
        when(nsplHistoryRepo.findAllByGeogcdIgnoreCaseAndStatus(nspl.getLaua(), "live")).thenReturn(nsplHistory);
        when(osService.getOsAddressData(postcode)).thenReturn(osResult);

        NsplAddress nsplAddress = nsplService.getAddressInfo(postcode);

        assertEquals(nsplAddress.getPostcode(), postcode);
        assertEquals(nsplAddress.getFourCharLaCode(), "a geogcdo");
        assertEquals(nsplAddress.getNineCharLaCode(), "a laua");
        assertEquals(nsplAddress.getAddresses(), osResult.get().getResults());

        verify(nsplRepo, atLeastOnce()).findAllByPcdIgnoreCase(postcode);
        verify(nsplRepo, atLeastOnce()).findAllByPcd2IgnoreCase(postcode);
        verify(nsplRepo, atLeastOnce()).findAllByPostcodeTrimmed(postcode);
        verify(nsplHistoryRepo, atLeastOnce()).findAllByGeogcdIgnoreCaseAndStatus(nspl.getLaua(), "live");
        verify(osService, atLeastOnce()).getOsAddressData(postcode);
    }

    @Test
    void shouldThrowNotFoundExceptionIfNoNsplResults() {
        when(nsplHistoryRepo.findAllByGeogcdIgnoreCaseAndStatus(nspl.getLaua(), "live")).thenReturn(nsplHistory);
        when(osService.getOsAddressData(postcode)).thenReturn(osResult);

        assertThrows(NotFoundException.class, () -> nsplService.getAddressInfo(postcode));
        verify(nsplRepo, atLeastOnce()).findAllByPcdIgnoreCase(postcode);
        verify(nsplRepo, atLeastOnce()).findAllByPcd2IgnoreCase(postcode);
        verify(nsplRepo, atLeastOnce()).findAllByPostcodeTrimmed(postcode);
        verify(nsplHistoryRepo, never()).findAllByGeogcdIgnoreCaseAndStatus(nspl.getLaua(), "live");
        verify(osService, never()).getOsAddressData(postcode);
    }

    @Test
    void shouldThrowNotFoundExceptionIfNoOsResults() {
        when(nsplRepo.findAllByPcdIgnoreCase(postcode)).thenReturn(Optional.of(nspl));
        when(nsplHistoryRepo.findAllByGeogcdIgnoreCaseAndStatus(nspl.getLaua(), "live")).thenReturn(nsplHistory);

        assertThrows(NotFoundException.class, () -> nsplService.getAddressInfo(postcode));
        verify(nsplRepo, atLeastOnce()).findAllByPcdIgnoreCase(postcode);
        verify(nsplRepo, never()).findAllByPcd2IgnoreCase(postcode);
        verify(nsplRepo, never()).findAllByPostcodeTrimmed(postcode);
        verify(nsplHistoryRepo, atLeastOnce()).findAllByGeogcdIgnoreCaseAndStatus(nspl.getLaua(), "live");
        verify(osService, atLeastOnce()).getOsAddressData(postcode);
    }
}
