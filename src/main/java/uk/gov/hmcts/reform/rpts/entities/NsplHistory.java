package uk.gov.hmcts.reform.rpts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "nspl_history")
@Getter
@Setter
@SuppressWarnings("PMD.TooManyFields")
public class NsplHistory {

    @Id
    private Integer id;
    @Column(name = "geogcd")
    private String geogcd; // Nine-character UK Government Statistical Service code
    @Column(name = "geognm")
    private String geognm; // Geography area name
    @Column(name = "geognmw")
    private String geognmw;
    @Column(name = "geogcdo")
    private String geogcdo; // The old ONS Geography code
    @Column(name = "geognmo")
    private String geognmo;
    @Column(name = "geogcdd")
    private String geogcdd;
    @Column(name = "geognmd")
    private String geognmd;
    @Column(name = "geogcdh")
    private String geogcdh;
    @Column(name = "geognmh")
    private String geognmh;
    @Column(name = "geogcds")
    private String geogcds;
    @Column(name = "geognms")
    private String geognms;
    @Column(name = "geogcdi")
    private String geogcdi;
    @Column(name = "geognmi")
    private String geognmi;
    @Column(name = "geogcdwg")
    private String geogcdwg;
    @Column(name = "geognmwg")
    private String geognmwg;
    @Column(name = "geognmwwg")
    private String geognmwwg;
    @Column(name = "oper_date")
    private String operDate;
    @Column(name = "term_date")
    private String termDate;
    @Column(name = "entitycd")
    private String entityCd;
    @Column(name = "year")
    private String year;
    @Column(name = "status")
    private String status;
}
