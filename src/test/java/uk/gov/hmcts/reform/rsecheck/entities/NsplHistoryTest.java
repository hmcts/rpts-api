package uk.gov.hmcts.reform.rsecheck.entities;

import org.junit.jupiter.api.Test;
import uk.gov.hmcts.reform.rpts.entities.NsplHistory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NsplHistoryTest {

    @Test
    void nsplHistoryEntityAllArgsTest() {

        NsplHistory nspl = new NsplHistory();
        nspl.setId(1);
        nspl.setGeogcd("geogcd");
        nspl.setGeognm("geognm");
        nspl.setGeognmw("geognmw");
        nspl.setGeogcdo("geogcdo");
        nspl.setGeognmo("geognmo");
        nspl.setGeogcdd("geogcdd");
        nspl.setGeognmd("geognmd");
        nspl.setGeogcdh("geogcdh");
        nspl.setGeognmh("geognmh");
        nspl.setGeogcds("geogcds");
        nspl.setGeognms("geognms");
        nspl.setGeogcdi("geogcdi");
        nspl.setGeognmi("geognmi");
        nspl.setGeogcdwg("geogcdwg");
        nspl.setGeognmwg("geognmwg");
        nspl.setGeognmwwg("geognmwwg");
        nspl.setOperDate("operDate");
        nspl.setTermDate("termDate");
        nspl.setEntityCd("entityCd");
        nspl.setYear("year");
        nspl.setStatus("status");

        assertEquals(1, nspl.getId(), "id does not match");
        assertEquals("geogcd", nspl.getGeogcd(), "geogcd does not match");
        assertEquals("geognm", nspl.getGeognm(), "geognm does not match");
        assertEquals("geognmw", nspl.getGeognmw(), "geognmw does not match");
        assertEquals("geogcdo", nspl.getGeogcdo(), "geogcdo does not match");
        assertEquals("geognmo", nspl.getGeognmo(), "geognmo does not match");
        assertEquals("geogcdd", nspl.getGeogcdd(), "geogcdd does not match");
        assertEquals("geognmd", nspl.getGeognmd(), "geognmd does not match");
        assertEquals("geogcdh", nspl.getGeogcdh(), "geogcdh does not match");
        assertEquals("geognmh", nspl.getGeognmh(), "geognmh does not match");
        assertEquals("geogcds", nspl.getGeogcds(), "geogcds does not match");
        assertEquals("geognms", nspl.getGeognms(), "geognms does not match");
        assertEquals("geogcds", nspl.getGeogcds(), "geogcds does not match");
        assertEquals("geognms", nspl.getGeognms(), "geognms does not match");
        assertEquals("geogcdi", nspl.getGeogcdi(), "geogcdi does not match");
        assertEquals("geognmi", nspl.getGeognmi(), "geognmi does not match");
        assertEquals("geogcdwg", nspl.getGeogcdwg(), "geogcdwg does not match");
        assertEquals("geognmwg", nspl.getGeognmwg(), "geognmwg does not match");
        assertEquals("geognmwwg", nspl.getGeognmwwg(), "geognmwwg does not match");
        assertEquals("operDate", nspl.getOperDate(), "operDate does not match");
        assertEquals("termDate", nspl.getTermDate(), "termDate does not match");
        assertEquals("entityCd", nspl.getEntityCd(), "entityCd does not match");
        assertEquals("year", nspl.getYear(), "year does not match");
        assertEquals("status", nspl.getStatus(), "status does not match");
    }
}
