package uk.gov.hmcts.reform.rpts.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nspl")
@Getter
@Setter
@SuppressWarnings({"PMD.ShortClassName", "PMD.TooManyFields"})
public class Nspl {

    @Id
    private int id;
    private String pcd;
    private String pcd2;
    private String pcds;
    private String doterm;
    private String oseast1m;
    private String osnrth1m;
    private String oa11;
    private String cty;
    private String ced;
    private String laua;
    private String ward;
    private String hlthau;
    private String nhser;
    private String ctry;
    private String rgn;
    private String pcon;
    private String eer;
    private String teclec;
    private String ttwa;
    private String pct;
    private String itl;
    private String park;
    private String lsoa11;
    private String msoa11;
    private String wz11;
    private String ccg;
    private String bua11;
    private String buasd11;
    private String ru11ind;
    private String oac11;
    private Double lat;
    @Column(name = "long")
    private Double lon;
    private String lep1;
    private String lep2;
    private String pfa;
    private String calncv;
    private String stp;
    private int dointr;
    private int usertype;
    private int osgrdind;
    private int imd;
}
