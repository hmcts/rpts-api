package uk.gov.hmcts.reform.rpts.exceptions;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6579941826346533850L;

    public NotFoundException(String searchCriteria) {
        super("Not found: " + searchCriteria);
    }
}
