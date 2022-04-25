package uk.gov.hmcts.reform.rsecheck.entities;

import org.junit.jupiter.api.Test;
import uk.gov.hmcts.reform.rpts.entities.Nspl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("PMD.NcssCount")
class NsplTest {

    @Test
    void nsplEntityAllArgsTest() {
        Nspl nspl = new Nspl();
        nspl.setId(1);
        nspl.setPcd("pcd");
        nspl.setPcd2("pcd2");
        nspl.setPcds("pcds");
        nspl.setDoterm("doterm");
        nspl.setOseast1m("oseast1m");
        nspl.setOsnrth1m("osnrth1m");
        nspl.setOa11("oa11");
        nspl.setCty("cty");
        nspl.setCed("ced");
        nspl.setLaua("laua");
        nspl.setWard("ward");
        nspl.setHlthau("hlthau");
        nspl.setNhser("nhser");
        nspl.setCtry("ctry");
        nspl.setRgn("rgn");
        nspl.setPcon("pcon");
        nspl.setEer("eer");
        nspl.setTeclec("teclec");
        nspl.setTtwa("ttwa");
        nspl.setPct("pct");
        nspl.setItl("itl");
        nspl.setPark("park");
        nspl.setLsoa11("lsoa11");
        nspl.setMsoa11("msoa11");
        nspl.setWz11("wz11");
        nspl.setCcg("ccg");
        nspl.setBua11("bua11");
        nspl.setBuasd11("buasd11");
        nspl.setRu11ind("ru11ind");
        nspl.setOac11("oac11");
        nspl.setLat(12.0);
        nspl.setLon(13.0);
        nspl.setLep1("lep1");
        nspl.setLep2("lep2");
        nspl.setPfa("pfa");
        nspl.setCalncv("calncv");
        nspl.setStp("stp");
        nspl.setDointr(0);
        nspl.setUsertype(1);
        nspl.setOsgrdind(2);
        nspl.setImd(3);

        assertEquals(1, nspl.getId(), "id does not match");
        assertEquals("pcd", nspl.getPcd(), "pcd does not match");
        assertEquals("pcd2", nspl.getPcd2(), "pcd2 does not match");
        assertEquals("pcds", nspl.getPcds(), "pcds does not match");
        assertEquals("doterm", nspl.getDoterm(), "doterm does not match");
        assertEquals("oseast1m", nspl.getOseast1m(), "oseast1m does not match");
        assertEquals("osnrth1m", nspl.getOsnrth1m(), "osnrth1m does not match");
        assertEquals("oa11", nspl.getOa11(), "oa11 does not match");
        assertEquals("cty", nspl.getCty(), "cty does not match");
        assertEquals("ced", nspl.getCed(), "ced does not match");
        assertEquals("laua", nspl.getLaua(), "laua does not match");
        assertEquals("ward", nspl.getWard(), "ward does not match");
        assertEquals("hlthau", nspl.getHlthau(), "hlthau does not match");
        assertEquals("nhser", nspl.getNhser(), "nhser does not match");
        assertEquals("ctry", nspl.getCtry(), "ctry does not match");
        assertEquals("rgn", nspl.getRgn(), "rgn does not match");
        assertEquals("pcon", nspl.getPcon(), "pcon does not match");
        assertEquals("eer", nspl.getEer(), "eer does not match");
        assertEquals("teclec", nspl.getTeclec(), "teclec does not match");
        assertEquals("ttwa", nspl.getTtwa(), "ttwa does not match");
        assertEquals("pct", nspl.getPct(), "pct does not match");
        assertEquals("itl", nspl.getItl(), "itl does not match");
        assertEquals("park", nspl.getPark(), "park does not match");
        assertEquals("lsoa11", nspl.getLsoa11(), "lsoa11 does not match");
        assertEquals("msoa11", nspl.getMsoa11(), "msoa11 does not match");
        assertEquals("wz11", nspl.getWz11(), "wz11 does not match");
        assertEquals("ccg", nspl.getCcg(), "ccg does not match");
        assertEquals("bua11", nspl.getBua11(), "bua11 does not match");
        assertEquals("buasd11", nspl.getBuasd11(), "buasd11 does not match");
        assertEquals("ru11ind", nspl.getRu11ind(), "ru11ind does not match");
        assertEquals("oac11", nspl.getOac11(), "oac11 does not match");
        assertEquals(12.0, nspl.getLat(), "lat does not match");
        assertEquals(13.0, nspl.getLon(), "lon does not match");
        assertEquals("lep1", nspl.getLep1(), "lep1 does not match");
        assertEquals("lep2", nspl.getLep2(), "lep2 does not match");
        assertEquals("pfa", nspl.getPfa(), "pfa does not match");
        assertEquals("calncv", nspl.getCalncv(), "calncv does not match");
        assertEquals("stp", nspl.getStp(), "stp does not match");
        assertEquals(0, nspl.getDointr(), "dointr does not match");
        assertEquals(1, nspl.getUsertype(), "usertype does not match");
        assertEquals(2, nspl.getOsgrdind(), "osgrdind does not match");
        assertEquals(3, nspl.getImd(), "imd does not match");
    }
}
