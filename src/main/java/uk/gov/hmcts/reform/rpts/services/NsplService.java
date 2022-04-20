package uk.gov.hmcts.reform.rpts.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.rpts.entities.Nspl;
import uk.gov.hmcts.reform.rpts.exceptions.NotFoundException;
import uk.gov.hmcts.reform.rpts.models.NsplAddress;
import uk.gov.hmcts.reform.rpts.repositories.NsplHistoryRepo;
import uk.gov.hmcts.reform.rpts.repositories.NsplRepo;

import java.util.Optional;

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

    public NsplAddress getAddressInfo(String postcode) {
        Nspl nspl = Optional.of(nsplRepo.findAllByPcdIgnoreCase(postcode.replace(" ", ""))
                                    .orElseThrow(() -> new NotFoundException(
                                        "Postcode not found on NSPL database: " + postcode))).get();
        return new NsplAddress(nspl.getPcd(), nspl.getLaua(),
                               nsplHistoryRepo.findAllByGEOGCDIgnoreCaseAndSTATUS(nspl.getLaua(), "live").getGEOGCDO(),
                               osService.getOsAddressData(postcode)
                                   .orElseThrow(() -> new NotFoundException("Postcode not found on OS: " + postcode)));
    }
}
