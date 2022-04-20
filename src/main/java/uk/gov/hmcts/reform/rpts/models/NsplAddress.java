package uk.gov.hmcts.reform.rpts.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uk.gov.hmcts.reform.rpts.os.OsResult;

@AllArgsConstructor
@Getter
@Setter
public class NsplAddress {

    private String postcode;
    private String fourCharLaCode;
    private String nineCharLaCode;
    private OsResult addresses;
}
