package uk.gov.hmcts.reform.rpts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.hmcts.reform.rpts.entities.Nspl;
import uk.gov.hmcts.reform.rpts.entities.NsplHistory;

import java.util.List;

public interface NsplHistoryRepo extends JpaRepository<NsplHistory, Integer> {
    NsplHistory findAllByGEOGCDIgnoreCaseAndSTATUS(String laua, String status);
}
