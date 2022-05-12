package uk.gov.hmcts.reform.rpts.repositories;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uk.gov.hmcts.reform.rpts.entities.Nspl;
import uk.gov.hmcts.reform.rpts.exceptions.NotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@SuppressWarnings({"PMD.ExcessiveParameterList"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

 class NsplRepositoryTest {
    String postcode = "TQ1 1BS";
    String exceptionMessage = "Test failed as row not found.";

    @Autowired
    private NsplRepo nsplRepository;

    @BeforeAll
    void beforeAll() {
        // Add a row for the tests to work on so that the
        // new repo functions can be checked
        addNsplRow(1, "TQ1 1BS", "TQ1  1BS", postcode, 198_001, "",
                   1, "291855", "0063630", 1, "E00077165",
                   "E99999999", "E99999999", "E06000027",
                   "E05012268", "E18000010", "E40000006", "E92000001",
                   "E12000009", "E14000999", "E15000009", "E24000032",
                   "E30000279", "E17000003", "E06000027", "E99999999",
                   "E01015267", "E02003163", "E33048020", "E38000230",
                   "E34004813", "E35001315", "C1", "5B1", 50.462_49,
                   -3.525_003, "E37000016", "", "E23000035", 1702,
                   "E56000014", "E54000037");
    }

    @Test
    void shouldRetrieveRowUsingPcdIgnoreCase() {
        final Nspl result = nsplRepository.findAllByPcdIgnoreCase(postcode)
            .orElseThrow(() -> new NotFoundException(exceptionMessage));
        assertThat(result).isNotNull();
        assertThat(result.getPcd()).isEqualTo("TQ1 1BS");

    }

    @Test
    void shouldRetrieveRowUsingPcd2IgnoreCaseSearch() {
        final Nspl result = nsplRepository.findAllByPcd2IgnoreCase("TQ1  1BS")
            .orElseThrow(() -> new NotFoundException(exceptionMessage));
        assertThat(result).isNotNull();
        assertThat(result.getPcd()).isEqualTo("TQ1 1BS");

    }

    @Test
    void shouldRetrieveRowUsingPcdTrimmedIgnoreCaseSearch() {
        final Nspl result = nsplRepository.findAllByPostcodeTrimmed("TQ11BS")
            .orElseThrow(() -> new NotFoundException(exceptionMessage));
        assertThat(result).isNotNull();
        assertThat(result.getPcd()).isEqualTo("TQ1 1BS");

    }

    @Test
    void shouldRetrieveRowUsingPcdsIgnoreCaseSearch() {
        final Nspl result = nsplRepository.findAllByPcdsIgnoreCase("TQ1 1BS")
            .orElseThrow(() -> new NotFoundException(exceptionMessage));
        assertThat(result).isNotNull();
        assertThat(result.getPcd()).isEqualTo("TQ1 1BS");
    }

    private void addNsplRow(int id, String pcd, String pcd2, String pcds, int dointr,
                            String doTerm, int userType, String osEast1m, String osNrth1m,
                            int osgrdInd, String oa11, String cty, String ced, String laua,
                            String ward, String hlthau, String nhser, String ctry,
                            String rgn, String pcon, String eer, String teclec,
                            String ttwa, String pct, String itl, String park, String lsoa11,
                            String msoa11, String wz11, String ccg, String bua11,
                            String buasd11, String ru11ind, String oac11, double lat, double lon,
                            String lep1, String lep2, String pfa, int imd, String calncv, String stp) {
        Nspl nspl = new Nspl();
        nspl.setId(id);
        nspl.setPcd(pcd);
        nspl.setPcd2(pcd2);
        nspl.setPcds(pcds);
        nspl.setDointr(dointr);
        nspl.setDoterm(doTerm);
        nspl.setUsertype(userType);
        nspl.setOseast1m(osEast1m);
        nspl.setOsnrth1m(osNrth1m);
        nspl.setOsgrdind(osgrdInd);
        nspl.setOa11(oa11);
        nspl.setCty(cty);
        nspl.setCed(ced);
        nspl.setLaua(laua);
        nspl.setWard(ward);
        nspl.setHlthau(hlthau);
        nspl.setNhser(nhser);
        nspl.setCtry(ctry);
        nspl.setRgn(rgn);
        nspl.setPcon(pcon);
        nspl.setEer(eer);
        nspl.setTeclec(teclec);
        nspl.setTtwa(ttwa);
        nspl.setPct(pct);
        nspl.setItl(itl);
        nspl.setPark(park);
        nspl.setLsoa11(lsoa11);
        nspl.setMsoa11(msoa11);
        nspl.setWz11(wz11);
        nspl.setCcg(ccg);
        nspl.setBua11(bua11);
        nspl.setBuasd11(buasd11);
        nspl.setRu11ind(ru11ind);
        nspl.setOac11(oac11);
        nspl.setLat(lat);
        nspl.setLon(lon);
        nspl.setLep1(lep1);
        nspl.setLep2(lep2);
        nspl.setPfa(pfa);
        nspl.setImd(imd);
        nspl.setCalncv(calncv);
        nspl.setStp(stp);
        nsplRepository.save(nspl);
    }
}
