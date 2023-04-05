package uk.gov.hmcts.reform.rpts.controllers;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.rpts.models.NsplAddress;
import uk.gov.hmcts.reform.rpts.services.NsplService;

import javax.validation.constraints.Pattern;

import static org.springframework.http.ResponseEntity.ok;

@RateLimiter(name = "default")
@RestController
@Validated
@RequestMapping(
    path = "/v1/search/address",
    produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class AddressController {

    private final NsplService nsplService;

    @Autowired
    public AddressController(NsplService nsplService) {
        this.nsplService = nsplService;
    }

    /**
     * Endpoint to retrieve the address, 4 char, and 9 char local authority codes.
     *
     * @param postcode The postcode required for the search
     * @return An NsplAddress entity which contains address lines and local authority information.
     */
    @GetMapping("/{postcode}")
    public ResponseEntity<NsplAddress> getAddress(
        @Pattern(regexp =
            "([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z]"
                + "[A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y]"
                + "[0-9][A-Za-z]?))))\\s?[0-9][A-Za-z]{2})",
            message = "Provided postcode is not valid")
        @PathVariable String postcode) {
        return ok(nsplService.getAddressInfo(postcode));
    }
}
