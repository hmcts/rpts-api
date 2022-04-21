package uk.gov.hmcts.reform.rpts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uk.gov.hmcts.reform.rpts.entities.Nspl;

import java.util.Optional;

public interface NsplRepo extends JpaRepository<Nspl, Integer> {
    @Query(nativeQuery = true, value =
        "SELECT id, pcd, pcd2, pcds, dointr, "
            + "doterm, usertype, oseast1m, osnrth1m, osgrdind, oa11, cty, "
            + "ced, laua, ward, hlthau, nhser, ctry, rgn, pcon, eer, teclec, "
            + "ttwa, pct, itl, park, lsoa11, msoa11, wz11, ccg, bua11, buasd11, "
            + "ru11ind, oac11, lat, long, lep1, lep2, pfa, imd, calncv, stp "
            + "FROM PUBLIC.nspl WHERE UPPER(REPLACE(pcd, ' ', '')) = UPPER(:postcode)" )
    Optional<Nspl> findAllByPostcodeTrimmed(String postcode);
    Optional<Nspl> findAllByPcdIgnoreCase(String postcode);
    Optional<Nspl> findAllByPcd2IgnoreCase(String postcode);
}
