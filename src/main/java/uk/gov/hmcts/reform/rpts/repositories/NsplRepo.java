package uk.gov.hmcts.reform.rpts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.hmcts.reform.rpts.entities.Nspl;

import java.util.Optional;

public interface NsplRepo extends JpaRepository<Nspl, Integer> {
    Optional<Nspl> findAllByPcdIgnoreCase(String postcode);
}
