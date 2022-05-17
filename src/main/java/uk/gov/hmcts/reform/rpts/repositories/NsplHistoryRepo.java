package uk.gov.hmcts.reform.rpts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.hmcts.reform.rpts.entities.NsplHistory;

import java.util.Optional;

public interface NsplHistoryRepo extends JpaRepository<NsplHistory, Integer> {
    Optional<NsplHistory> findAllByGeogcdIgnoreCaseAndStatus(String laua, String status);
}
