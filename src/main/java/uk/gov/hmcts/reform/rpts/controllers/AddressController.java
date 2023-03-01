package uk.gov.hmcts.reform.rpts.controllers;

import nonapi.io.github.classgraph.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.rpts.models.BulkPrintDocument;
import uk.gov.hmcts.reform.rpts.models.BulkPrintRequest;
import uk.gov.hmcts.reform.rpts.models.NsplAddress;
import uk.gov.hmcts.reform.rpts.services.BulkPrintService;
import uk.gov.hmcts.reform.rpts.services.NsplService;

import javax.validation.constraints.Pattern;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Validated
@RequestMapping(
    path = "/v1/search/address",
    produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class AddressController {

    private final NsplService nsplService;
    private final BulkPrintService bulkPrintService;

    @Autowired
    public AddressController(NsplService nsplService, BulkPrintService bulkPrintService) {
        this.nsplService = nsplService;
        this.bulkPrintService = bulkPrintService;
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

    @GetMapping("/test")
    public ResponseEntity<List<UUID>> test() throws IOException {


        BulkPrintDocument bulkPrintDocument = new BulkPrintDocument();
        bulkPrintDocument.setFileName("dummy.pdf");
        bulkPrintDocument.setBinaryFileUrl("dummy pdf url");
        BulkPrintDocument bulkPrintDocument2 = new BulkPrintDocument();
        bulkPrintDocument2.setFileName("dummy.pdf2");
        bulkPrintDocument2.setBinaryFileUrl("dummy pdf url2");

        BulkPrintRequest bulkPrintRequest = new BulkPrintRequest();
        bulkPrintRequest.setBulkPrintDocuments(Arrays.asList(bulkPrintDocument, bulkPrintDocument2));
        bulkPrintRequest.setCaseId("caseid");
        bulkPrintRequest.setLetterType("caselettertype");

        ClassLoader classLoader = AddressController.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("test_pdf.pdf")).getFile());
        File file2 = new File(Objects.requireNonNull(classLoader.getResource("test_pdf2.pdf")).getFile());

        List<UUID> uuidList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            try {

                UUID uuid = bulkPrintService.send(
                    bulkPrintRequest,
                    Arrays.asList(
                        Files.readAllBytes(file.toPath()),
                        Files.readAllBytes(file2.toPath())
                    )
                );
                uuidList.add(uuid);

            } catch (Exception ex) {
                UUID uuid = bulkPrintService.send(
                    bulkPrintRequest,
                    Arrays.asList(
                        Files.readAllBytes(file.toPath()),
                        Files.readAllBytes(file2.toPath())
                    )
                );
                uuidList.add(uuid);
            }
        }
        return ResponseEntity.ok(uuidList);
    }
}
