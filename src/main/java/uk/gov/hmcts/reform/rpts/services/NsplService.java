package uk.gov.hmcts.reform.rpts.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.rpts.entities.Nspl;
import uk.gov.hmcts.reform.rpts.exceptions.NotFoundException;
import uk.gov.hmcts.reform.rpts.models.NsplAddress;
import uk.gov.hmcts.reform.rpts.repositories.NsplHistoryRepo;
import uk.gov.hmcts.reform.rpts.repositories.NsplRepo;

@Service
@Slf4j
public class NsplService {

    private final NsplRepo nsplRepo;
    private final NsplHistoryRepo nsplHistoryRepo;
    private final OsService osService;

    @Autowired
    public NsplService(NsplRepo nsplRepo,
                       NsplHistoryRepo nsplHistoryRepo,
                       OsService osService) {
        this.nsplRepo = nsplRepo;
        this.nsplHistoryRepo = nsplHistoryRepo;
        this.osService = osService;
    }

    /**
     * Attempt to get address information from Ordinance Survey, and the
     * 4 character and 9 character local authority code.
     * For the 9 char code, there are different search paths using different columns
     * within the Nspl table. If one is not successful, it will move on to the
     * next.
     *
     * @param postcode The postcode required to get the necessary information
     * @return The address, laua (9 char code) and the Geogcd (4 char la code)
     */
    public NsplAddress getAddressInfo(String postcode) {
        log.info("Performing query for address search with postcode: {}", postcode);
        Nspl nspl = nsplRepo.findAllByPcdIgnoreCase(postcode)                // search by pcd
            .orElseGet(() -> nsplRepo.findAllByPcd2IgnoreCase(postcode)      // search by pcd2
                .orElseGet(() -> nsplRepo.findAllByPcdsIgnoreCase(postcode)  // search by pcds
                    .orElseGet(() -> nsplRepo.findAllByPostcodeTrimmed(postcode) // If no row, remove spaces from pcd
                        .orElseThrow(() -> new NotFoundException("Postcode not found on Database: " + postcode)))));

        log.debug("Returned from NSPL DB: laua: {}, postcode: {}", nspl.getLaua(), nspl.getPcd());
        return new NsplAddress(nspl.getPcd(),
                               nsplHistoryRepo.findAllByGeogcdIgnoreCaseAndStatus(nspl.getLaua(), "live")
                                   .orElseThrow(() -> new NotFoundException("4 char code not found on Database: "
                                                                                + postcode)).getGeogcdo(),
                               nspl.getLaua(),
                               osService.getOsAddressData(postcode)
                                   .orElseThrow(() -> new NotFoundException("Postcode not found on OS: "
                                                                                + postcode)).getResults());
    }
}
