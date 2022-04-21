package uk.gov.hmcts.reform.rpts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.hmcts.reform.rpts.entities.NsplHistory;

public interface NsplHistoryRepo extends JpaRepository<NsplHistory, Integer> {
    NsplHistory findAllByGeogcdIgnoreCaseAndStatus(String laua, String status);
}
