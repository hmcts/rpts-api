//package uk.gov.hmcts.reform.rpts.config;
//
//import org.flywaydb.core.Flyway;
//import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
//import uk.gov.hmcts.reform.rpts.exceptions.PendingMigrationScriptException;
//
//import java.util.stream.Stream;
//
//public class FlywayNoOpStrategy implements FlywayMigrationStrategy {
//
//    @Override
//    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
//    public void migrate(Flyway flyway) {
//        Stream.of(flyway.info().all())
//            .filter(info -> !info.getState().isApplied())
//            .findFirst()
//            .ifPresent(info -> {
//                throw new PendingMigrationScriptException(info.getScript());
//            });
//    }
//}
