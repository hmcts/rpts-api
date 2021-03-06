package uk.gov.hmcts.reform.rpts.models;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NsplAddress {

    private String postcode;
    private String fourCharLaCode;
    private String nineCharLaCode;
    private List<JsonNode> addresses;
}
