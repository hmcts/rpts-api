package uk.gov.hmcts.reform.rpts.os;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class OsResult {

    @JsonProperty("results")
    List<JsonNode> results;
}
