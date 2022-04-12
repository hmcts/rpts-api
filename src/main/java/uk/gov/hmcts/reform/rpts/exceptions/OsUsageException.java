package uk.gov.hmcts.reform.rpts.exceptions;

public class OsUsageException extends RuntimeException {
    private static final long serialVersionUID = 6751592734307424106L;
    private static final String USAGE_LIMIT_REACHED = "Usage limit reached.";

    public OsUsageException() {
        super(USAGE_LIMIT_REACHED);
    }
}
