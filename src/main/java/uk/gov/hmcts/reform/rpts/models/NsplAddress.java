package uk.gov.hmcts.reform.rpts.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tools.jackson.databind.JsonNode;

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
