package cabanas.garcia.ismael.atm.infrastructure.repository;

import cabanas.garcia.ismael.atm.domain.model.Atm;
import cabanas.garcia.ismael.atm.domain.model.AtmId;
import cabanas.garcia.ismael.atm.domain.repository.AtmRepository;
import cabanas.garcia.ismael.snackmachine.infrastructure.framework.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static cabanas.garcia.ismael.shared.domain.model.Money.DOLLAR;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("integration-test")
public class PostgresAtmRepositoryShould {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AtmRepository atmRepository;

    @Autowired
    private AtmRowMapper atmRowMapper;

    @Transactional @Test public void
    save_atm() {
        Optional<Atm> atm = atmRepository.findById(new AtmId("1"));

        atm.ifPresent(atmM -> {
            atmM.loadMoney(DOLLAR);
            atmM.loadMoney(DOLLAR);
            atmM.withdraw(1.00);
            atmRepository.save(atmM);
        });

        Atm atmSaved = getAtmById(atm.get().id().getValue());
        assertThat(atmSaved.moneyInside()).isEqualTo(DOLLAR);
        assertThat(atmSaved.moneyCharged()).isEqualTo(1.01);
    }

    private Atm getAtmById(String id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ATM_MACHINE WHERE AM_ID = ? ",
                atmRowMapper, id);
    }
}
