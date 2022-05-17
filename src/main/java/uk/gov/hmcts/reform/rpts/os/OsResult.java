package uk.gov.hmcts.reform.rpts.os;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OsResult {

    @JsonProperty("results")
    private List<JsonNode> results;
}
