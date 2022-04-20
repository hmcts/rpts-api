package uk.gov.hmcts.reform.rpts.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "nspl_history")
@Getter
@Setter
public class NsplHistory {

    @Id
    private Integer id;
    private String GEOGCD;
    private String GEOGNM;
    private String GEOGNMW;
    private String GEOGCDO;
    private String GEOGNMO;
    private String GEOGCDD;
    private String GEOGNMD;
    private String GEOGCDH;
    private String GEOGNMH;
    private String GEOGCDS;
    private String GEOGNMS;
    private String GEOGCDI;
    private String GEOGNMI;
    private String GEOGCDWG;
    private String GEOGNMWG;
    private String GEOGNMWWG;
    private String OPER_DATE;
    private String TERM_DATE;
    private String ENTITYCD;
    private String YEAR;
    private String STATUS;
}
