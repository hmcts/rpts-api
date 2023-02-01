package uk.gov.hmcts.reform.rpts.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import uk.gov.hmcts.reform.rpts.entities.Nspl;
import uk.gov.hmcts.reform.rpts.entities.NsplHistory;
import uk.gov.hmcts.reform.rpts.repositories.NsplHistoryRepo;
import uk.gov.hmcts.reform.rpts.repositories.NsplRepo;

/**
 * This will be used to add rows to the test container thats spun up.
 * The reason for this is because the migrations will not be able to
 * add the 2 million rows of nspl information to the postgres instance
 * in preview (as its spun up and torn down each time).
 */
@ContextConfiguration
@SuppressWarnings({"PMD.ConstructorCallsOverridableMethod", "PMD.ExcessiveParameterList"})
public class DataSetupUtils {

    private final NsplRepo nsplRepo;
    private final NsplHistoryRepo nsplHistoryRepo;

    @Autowired
    public DataSetupUtils(NsplRepo nsplRepo, NsplHistoryRepo nsplHistoryRepo) {
        this.nsplRepo = nsplRepo;
        this.nsplHistoryRepo = nsplHistoryRepo;
        setUpData();
    }

    public void setUpData() {

        addNsplRow(1, "IP1 1EJ", "IP1  1EJ", "IP11EJ", 198_001, "",
                   1, "291855", "0063630", 1, "E00077165",
                   "E99999999", "E99999999", "E06000027",
                   "E05012268", "E18000010", "E40000006", "E92000001",
                   "E12000009", "E14000999", "E15000009", "E24000032",
                   "E30000279", "E17000003", "E06000027", "E99999999",
                   "E01015267", "E02003163", "E33048020", "E38000230",
                   "E34004813", "E35001315", "C1", "5B1", 50.462_49,
                   -3.525_003, "E37000016", "", "E23000035", 1702,
                   "E56000014", "E54000037");

        addNsplHistoryRow(1, "E06000027", "Torbay", "Torbay", "X1165",
                          "Torbay", "914", "Torbay Council", "29/11/2013 00:00:00",
                          "28/11/2013 00:00:00", "E06", "2013", "live", "00HH");
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
        nsplRepo.save(nspl);
    }

    private void addNsplHistoryRow(int id, String geogcd, String geognm, String geognmo,
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
