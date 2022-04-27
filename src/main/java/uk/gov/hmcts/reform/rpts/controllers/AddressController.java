package uk.gov.hmcts.reform.rpts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.rpts.models.NsplAddress;
import uk.gov.hmcts.reform.rpts.services.NsplService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
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
    public ResponseEntity<NsplAddress> getAddress(@PathVariable("postcode") String postcode) {
        return ok(nsplService.getAddressInfo(postcode));
    }
}
