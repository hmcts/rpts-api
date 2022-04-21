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
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NsplService.class)
class NsplServiceTest {

    @MockBean
    private NsplRepo nsplRepo;
    @MockBean
    private NsplHistoryRepo nsplHistoryRepo;
    @MockBean
    private OsService osService;

    @Autowired
    private NsplService nsplService;

    private static final Nspl NSPL = new Nspl();
    private static final NsplHistory NSPL_HISTORY = new NsplHistory();
    private static final String POSTCODE = "PL5 1AA";
    private static final String LIVE = "live";
    private static final String LAUA = "a laua";
    private static final String GEOGCDO = "a geogcdo";
    private static final String POSTCODE_MISMATCH = "Postcode does not match";
    private static final String FOUR_CHAR_MISMATCH = "4 char la code should match";
    private static final String NINE_CHAR_MISMATCH = "9 char la code should match";
    private static final String OS_MISMATCH = "OS results should match";
    private static Optional<OsResult> osResult;

    @BeforeAll
    static void beforeAll() throws IOException {
        NSPL.setLaua(LAUA);
        NSPL.setPcd(POSTCODE);
        NSPL_HISTORY.setGeogcdo(GEOGCDO);

        Path path = Paths.get("src/test/resources/os_address_example.json");
        osResult = Optional.of(new ObjectMapper().readValue(path.toFile(), OsResult.class));
    }

    @Test
    void shouldReturnResultsWithPcdIgnoreCaseSearch() {
        when(nsplRepo.findAllByPcdIgnoreCase(POSTCODE)).thenReturn(Optional.of(NSPL));
        when(nsplHistoryRepo.findAllByGeogcdIgnoreCaseAndStatus(NSPL.getLaua(), LIVE)).thenReturn(NSPL_HISTORY);
        when(osService.getOsAddressData(POSTCODE)).thenReturn(osResult);

        NsplAddress nsplAddress = nsplService.getAddressInfo(POSTCODE);

        assertEquals(nsplAddress.getPostcode(), POSTCODE, POSTCODE_MISMATCH);
        assertEquals(nsplAddress.getFourCharLaCode(), GEOGCDO, FOUR_CHAR_MISMATCH);
        assertEquals(nsplAddress.getNineCharLaCode(), LAUA, NINE_CHAR_MISMATCH);
        assertEquals(nsplAddress.getAddresses(), osResult.get().getResults(), OS_MISMATCH);

        verify(nsplRepo, atLeastOnce()).findAllByPcdIgnoreCase(POSTCODE);
        verify(nsplRepo, never()).findAllByPcd2IgnoreCase(POSTCODE);
        verify(nsplRepo, never()).findAllByPostcodeTrimmed(POSTCODE);
        verify(nsplHistoryRepo, atLeastOnce()).findAllByGeogcdIgnoreCaseAndStatus(NSPL.getLaua(), LIVE);
        verify(osService, atLeastOnce()).getOsAddressData(POSTCODE);
    }

    @Test
    void shouldReturnResultsWithPcd2IgnoreCaseSearch() {
        when(nsplRepo.findAllByPcd2IgnoreCase(POSTCODE)).thenReturn(Optional.of(NSPL));
        when(nsplHistoryRepo.findAllByGeogcdIgnoreCaseAndStatus(NSPL.getLaua(), LIVE)).thenReturn(NSPL_HISTORY);
        when(osService.getOsAddressData(POSTCODE)).thenReturn(osResult);

        NsplAddress nsplAddress = nsplService.getAddressInfo(POSTCODE);

        assertEquals(nsplAddress.getPostcode(), POSTCODE, POSTCODE_MISMATCH);
        assertEquals(nsplAddress.getFourCharLaCode(), GEOGCDO, FOUR_CHAR_MISMATCH);
        assertEquals(nsplAddress.getNineCharLaCode(), LAUA, NINE_CHAR_MISMATCH);
        assertEquals(nsplAddress.getAddresses(), osResult.get().getResults(), OS_MISMATCH);

        verify(nsplRepo, atLeastOnce()).findAllByPcdIgnoreCase(POSTCODE);
        verify(nsplRepo, atLeastOnce()).findAllByPcd2IgnoreCase(POSTCODE);
        verify(nsplRepo, never()).findAllByPostcodeTrimmed(POSTCODE);
        verify(nsplHistoryRepo, atLeastOnce()).findAllByGeogcdIgnoreCaseAndStatus(NSPL.getLaua(), LIVE);
        verify(osService, atLeastOnce()).getOsAddressData(POSTCODE);
    }

    @Test
    void shouldReturnResultsWithPcdTrimmedSearch() {
        when(nsplRepo.findAllByPostcodeTrimmed(POSTCODE)).thenReturn(Optional.of(NSPL));
        when(nsplHistoryRepo.findAllByGeogcdIgnoreCaseAndStatus(NSPL.getLaua(), LIVE)).thenReturn(NSPL_HISTORY);
        when(osService.getOsAddressData(POSTCODE)).thenReturn(osResult);

        NsplAddress nsplAddress = nsplService.getAddressInfo(POSTCODE);

        assertEquals(nsplAddress.getPostcode(), POSTCODE, POSTCODE_MISMATCH);
        assertEquals(nsplAddress.getFourCharLaCode(), GEOGCDO, FOUR_CHAR_MISMATCH);
        assertEquals(nsplAddress.getNineCharLaCode(), LAUA, NINE_CHAR_MISMATCH);
        assertEquals(nsplAddress.getAddresses(), osResult.get().getResults(), OS_MISMATCH);

        verify(nsplRepo, atLeastOnce()).findAllByPcdIgnoreCase(POSTCODE);
        verify(nsplRepo, atLeastOnce()).findAllByPcd2IgnoreCase(POSTCODE);
        verify(nsplRepo, atLeastOnce()).findAllByPostcodeTrimmed(POSTCODE);
        verify(nsplHistoryRepo, atLeastOnce()).findAllByGeogcdIgnoreCaseAndStatus(NSPL.getLaua(), LIVE);
        verify(osService, atLeastOnce()).getOsAddressData(POSTCODE);
    }

    @Test
    void shouldThrowNotFoundExceptionIfNoNsplResults() {
        when(nsplHistoryRepo.findAllByGeogcdIgnoreCaseAndStatus(NSPL.getLaua(), LIVE)).thenReturn(NSPL_HISTORY);
        when(osService.getOsAddressData(POSTCODE)).thenReturn(osResult);

        assertThrows(NotFoundException.class, () -> nsplService.getAddressInfo(POSTCODE));
        verify(nsplRepo, atLeastOnce()).findAllByPcdIgnoreCase(POSTCODE);
        verify(nsplRepo, atLeastOnce()).findAllByPcd2IgnoreCase(POSTCODE);
        verify(nsplRepo, atLeastOnce()).findAllByPostcodeTrimmed(POSTCODE);
        verify(nsplHistoryRepo, never()).findAllByGeogcdIgnoreCaseAndStatus(NSPL.getLaua(), LIVE);
        verify(osService, never()).getOsAddressData(POSTCODE);
    }

    @Test
    void shouldThrowNotFoundExceptionIfNoOsResults() {
        when(nsplRepo.findAllByPcdIgnoreCase(POSTCODE)).thenReturn(Optional.of(NSPL));
        when(nsplHistoryRepo.findAllByGeogcdIgnoreCaseAndStatus(NSPL.getLaua(), LIVE)).thenReturn(NSPL_HISTORY);

        assertThrows(NotFoundException.class, () -> nsplService.getAddressInfo(POSTCODE));
        verify(nsplRepo, atLeastOnce()).findAllByPcdIgnoreCase(POSTCODE);
        verify(nsplRepo, never()).findAllByPcd2IgnoreCase(POSTCODE);
        verify(nsplRepo, never()).findAllByPostcodeTrimmed(POSTCODE);
        verify(nsplHistoryRepo, atLeastOnce()).findAllByGeogcdIgnoreCaseAndStatus(NSPL.getLaua(), LIVE);
        verify(osService, atLeastOnce()).getOsAddressData(POSTCODE);
    }
}
