package uk.gov.hmcts.reform.rpts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "nspl")
@Getter
@Setter
@SuppressWarnings({"PMD.ShortClassName", "PMD.TooManyFields"})
public class Nspl {

    @Id
    private int id;
    private String pcd; // 7-character version of the postcode (e.g. 'BT1 1AA', 'BT486PL')
    private String pcd2; // 8-character version of the postcode (e.g. 'BT1  1AA', 'BT48 6PL')
    private String pcds; // one space between the district and sector-unit part of the postcode (BT1 1AA' as 'BT48 6PL')
    private String doterm; // The date when the postcode was terminated (e.g. 199304).
    private String oseast1m; // Ordnance Survey Easting (X reference).
    private String osnrth1m; // Ordnance Survey Northing (Y reference).
    private String oa11; // 2011 Census output area.
    private String cty; // Counties
    private String ced;
    private String laua; // Local Authority District.
    private String ward;
    private String hlthau; // Strategic Health Authority
    private String nhser; // NHS England Region
    private String ctry; // Country
    private String rgn; // Region
    private String pcon; // Parliamentary constituency
    private String eer; // European Electoral Region
    private String teclec; // LLSC/DCELLS/ER
    private String ttwa; // Travel to work area
    private String pct; // Primary Care Organisation
    private String itl; // International Territorial Level
    private String park; // National Park
    private String lsoa11; // Lower layer SOA (LSOA)
    private String msoa11; // Middle layer SOA (MSOA)
    private String wz11; // Workplace zone
    private String ccg; // Clinical Commissioning Group
    private String bua11; // Built-up area
    private String buasd11; // Built-up area sub-division
    private String ru11ind; // 2011 Census rural-urban classification
    private String oac11; // OA classification
    private Double lat; // Latitude
    @Column(name = "long")
    private Double lon; // Longitude
    private String lep1; // Local Enterprise Partnership 1
    private String lep2; // Local Enterprise Partnership 2
    private String pfa; // Police Force Area
    private String calncv; // Cancer Alliances / National Cancer Vanguards
    private String stp; // Sustainability and Transformation Partnerships
    private int dointr; // Date of introduction
    private int usertype;
    private int osgrdind; // Grid ref PQI
    private int imd;
}
