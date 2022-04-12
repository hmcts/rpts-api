package uk.gov.hmcts.reform.rpts.os;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "osApi", url = "${os.url}")
public interface OsClient {
    @GetMapping("${os.endpoint.postcode-search}?postcode={postcode}")
    OsResult getPostcodeData(@PathVariable("postcode") String postcode);
}
