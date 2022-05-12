package uk.gov.hmcts.reform.rpts.repositories;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uk.gov.hmcts.reform.rpts.entities.NsplHistory;
import uk.gov.hmcts.reform.rpts.exceptions.NotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@SuppressWarnings({"PMD.ExcessiveParameterList"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class NsplHistoryRepositoryTest {

    @Autowired
    private NsplHistoryRepo nsplHistoryRepo;

    @BeforeAll
    void beforeAll() {
        // Add a row for the tests to work on so that the
        // new repo functions can be checked
        addNsplHistoryRow(1, "E06000027", "Torbay", "Torbay", "X1165",
                          "Torbay", "914", "Torbay Council", "29/11/2013 00:00:00",
                          "28/11/2013 00:00:00", "E06", "2013", "live", "00HH");
    }

    @Test
    void shouldRetrieveRowByGeogcdIgnoreCaseAndStatus() {
        final NsplHistory result = nsplHistoryRepo.findAllByGeogcdIgnoreCaseAndStatus("E06000027", "live")
            .orElseThrow(() -> new NotFoundException("Test failed as row not found."));
        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo("live");
        assertThat(result.getGeogcd()).isEqualTo("E06000027");

    }

    void addNsplHistoryRow(int id, String geogcd, String geognm, String geognmo,
                                   String geogcdd, String geognmd, String geogcdh, String geognmh, String operDate,
                                   String termDate, String entityCd, String year, String status, String geogcdo) {
        NsplHistory nsplHistory = new NsplHistory();
        nsplHistory.setId(id);
        nsplHistory.setGeogcd(geogcd);
        nsplHistory.setGeognm(geognm);
        nsplHistory.setGeognmo(geognmo);
        nsplHistory.setGeogcdd(geogcdd);
        nsplHistory.setGeognmd(geognmd);
        nsplHistory.setGeogcdh(geogcdh);
        nsplHistory.setGeognmh(geognmh);
        nsplHistory.setOperDate(operDate);
        nsplHistory.setTermDate(termDate);
        nsplHistory.setEntityCd(entityCd);
        nsplHistory.setYear(year);
        nsplHistory.setStatus(status);
        nsplHistory.setGeogcdo(geogcdo);
        nsplHistoryRepo.save(nsplHistory);
    }

}
