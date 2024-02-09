package uk.gov.hmcts.reform.rpts.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;
import uk.gov.hmcts.reform.rpts.models.BulkPrintDocument;
import uk.gov.hmcts.reform.rpts.models.BulkPrintRequest;
import uk.gov.hmcts.reform.sendletter.api.LetterWithPdfsRequest;
import uk.gov.hmcts.reform.sendletter.api.SendLetterApi;
import uk.gov.hmcts.reform.sendletter.api.SendLetterResponse;
import uk.gov.hmcts.reform.sendletter.api.model.v3.Document;
import uk.gov.hmcts.reform.sendletter.api.model.v3.LetterV3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Base64.getEncoder;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class BulkPrintService {

    //https://github.com/hmcts/finrem-case-orchestration-service
    private static final String XEROX_TYPE_PARAMETER = "FINREM001";
    private static final String LETTER_TYPE_KEY = "letterType";
    private static final String CASE_REFERENCE_NUMBER_KEY = "caseReferenceNumber";
    private static final String CASE_IDENTIFIER_KEY = "caseIdentifier";
    private static final String RECIPIENTS = "recipients";

    private static final String FILE_NAMES = "fileNames";

    //    private final AuthTokenGenerator authTokenGenerator;
    private final SendLetterApi sendLetterApi;

    /**
     * Note: the order of documents you send to this service is the order in which they will print.
     */

    public UUID send(final BulkPrintRequest bulkPrintRequest, final List<byte[]> listOfDocumentsAsByteArray) throws IOException {

        String letterType = bulkPrintRequest.getLetterType();
        String caseId = bulkPrintRequest.getCaseId();

        log.info("Sending {} for case {}", letterType, caseId);

        final List<Document> documents = listOfDocumentsAsByteArray.stream()
            .map(docBytes -> new Document(getEncoder().encodeToString(docBytes), 3))
            .collect(toList());

        SendLetterResponse sendLetterResponse = sendLetterApi.sendLetter(
            "test",
            new LetterV3("a type",
                         documents,
                         getAdditionalData(caseId, letterType, bulkPrintRequest)
            )
        );

        log.info("Letter service produced the following letter Id {} for case {}", sendLetterResponse.letterId, caseId);
        return sendLetterResponse.letterId;
    }

    @SuppressWarnings({"PMD.ExcessiveParameterList"})
    private Map<String, Object> getAdditionalData(final String caseId, final String letterType,
                                                  final BulkPrintRequest bulkPrintRequest) {
        final Map<String, Object> additionalData = new HashMap<>();
//        additionalData.put(LETTER_TYPE_KEY, UUID.randomUUID());

//        1448915163945522585
//        test_first_name test_middle_name test_last_name
//        general-letter
//        d5aa76e1-158c-431e-90f3-d43cd72f34f0

        additionalData.put(CASE_IDENTIFIER_KEY, "1448915163945522589");
        additionalData.put(CASE_REFERENCE_NUMBER_KEY, "1448915163945522588");
        additionalData.put("letterType", "general-letter");
        additionalData.put("isInternational", true);

//        additionalData.put(FILE_NAMES, getFileNames(bulkPrintRequest));
        additionalData.put(RECIPIENTS, Arrays.asList("Gilligan Blobbers", "Querky Mcgibbins",
                                                     UUID.randomUUID(), UUID.randomUUID()));
//        additionalData.put(RECIPIENTS, Arrays.asList("Respondent FN LN"));
//        additionalData.put(RECIPIENTS, Arrays.asList("OTHER PERSON FN LN"));
        return additionalData;

//        {"recipients":["1692693646370588","Test  User","respondent-aos-pack"],
//            "caseReferenceNumber":"1692693646370588","letterType":"respondent-aos-pack"
//            ,"caseIdentifier":"1692693646370588"}

//        {"recipients":["1692693646370588","Test  User","applicant-aos-pack"],
//            "caseReferenceNumber":"1692693646370588","letterType":"applicant-aos-pack",
//            "caseIdentifier":"1692693646370588"}
    }

    private List<String> getFileNames(BulkPrintRequest bulkPrintRequest) {
        return bulkPrintRequest.getBulkPrintDocuments().stream().map(BulkPrintDocument::getFileName).collect(toList());
    }
}
