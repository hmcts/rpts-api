package uk.gov.hmcts.reform.rpts.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nspl_history")
@Getter
@Setter
@SuppressWarnings("PMD.TooManyFields")
public class NsplHistory {

    @Id
    private Integer id;
    @Column(name = "GEOGCD")
    private String geogcd; // Nine-character UK Government Statistical Service code
    @Column(name = "GEOGNM")
    private String geognm; // Geography area name
    @Column(name = "GEOGNMW")
    private String geognmw;
    @Column(name = "GEOGCDO")
    private String geogcdo; // The old ONS Geography code
    @Column(name = "GEOGNMO")
    private String geognmo;
    @Column(name = "GEOGCDD")
    private String geogcdd;
    @Column(name = "GEOGNMD")
    private String geognmd;
    @Column(name = "GEOGCDH")
    private String geogcdh;
    @Column(name = "GEOGNMH")
    private String geognmh;
    @Column(name = "GEOGCDS")
    private String geogcds;
    @Column(name = "GEOGNMS")
    private String geognms;
    @Column(name = "GEOGCDI")
    private String geogcdi;
    @Column(name = "GEOGNMI")
    private String geognmi;
    @Column(name = "GEOGCDWG")
    private String geogcdwg;
    @Column(name = "GEOGNMWG")
    private String geognmwg;
    @Column(name = "GEOGNMWWG")
    private String geognmwwg;
    @Column(name = "OPER_DATE")
    private String operDate;
    @Column(name = "TERM_DATE")
    private String termDate;
    @Column(name = "ENTITYCD")
    private String entityCd;
    @Column(name = "YEAR")
    private String year;
    @Column(name = "STATUS")
    private String status;
}
