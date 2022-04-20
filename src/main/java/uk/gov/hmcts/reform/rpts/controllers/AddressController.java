package uk.gov.hmcts.reform.rpts.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.rpts.models.NsplAddress;
import uk.gov.hmcts.reform.rpts.os.OsResult;
import uk.gov.hmcts.reform.rpts.services.NsplService;
import uk.gov.hmcts.reform.rpts.services.OsService;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(
    path = "/v1/search/address",
    produces = {MediaType.APPLICATION_JSON_VALUE}
)
@Slf4j
public class AddressController {

    private final OsService osService;
    private final NsplService nsplService;

    @Autowired
    public AddressController(OsService osService, NsplService nsplService) {
        this.osService = osService;
        this.nsplService = nsplService;
    }

    @GetMapping("/{postcode}")
    public ResponseEntity<Optional<OsResult>> getAddress(@PathVariable("postcode") String postcode) {

        log.info("postcode in search is: " + postcode);

        return ok(osService.getOsAddressData(postcode));
    }

    @GetMapping("test/{postcode}")
    public ResponseEntity<NsplAddress> getAddressTest(@PathVariable("postcode") String postcode) {

        log.info("postcode in search is: " + postcode);
        return ok(nsplService.getAddressInfo(postcode));
    }
}
