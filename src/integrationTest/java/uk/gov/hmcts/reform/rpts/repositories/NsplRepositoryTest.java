package uk.gov.hmcts.reform.rpts.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uk.gov.hmcts.reform.rpts.entities.Nspl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest

public class NsplRepositoryTest {

    @Autowired
    private NsplRepo nsplRepository;

    @Test
    void shouldRetrieveData() {

        final List<Nspl> results = nsplRepository.findAll();
        System.out.println("................+++++++++++++++" + results.size());


//        assertThat(results).hasSizeGreaterThan(1);
//        assertThat(results.stream().map(r -> r.getPcd())).isNotEmpty();
//        assertThat(results.stream().map(r -> r.getPcd2())).isNotEmpty();


    }

}
