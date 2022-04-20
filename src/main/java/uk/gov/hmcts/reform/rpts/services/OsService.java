package uk.gov.hmcts.reform.rpts.services;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.rpts.os.OsClient;
import uk.gov.hmcts.reform.rpts.os.OsResult;

import java.util.Optional;

@Service
@Slf4j
public class OsService {

    private final OsClient osClient;

    @Autowired
    public OsService(final OsClient osClient) {
        this.osClient = osClient;
    }

    public Optional<OsResult> getOsAddressData(final String postcode) {

        if (!postcode.isBlank()) {
            try {
                return Optional.of(osClient.getPostcodeData(postcode));
            } catch (final FeignException ex) {
                log.warn("HTTP Status: {} Message: {}", ex.status(), ex.getMessage(), ex);
                throw ex;
            }
        }

        return Optional.empty();
    }
}
