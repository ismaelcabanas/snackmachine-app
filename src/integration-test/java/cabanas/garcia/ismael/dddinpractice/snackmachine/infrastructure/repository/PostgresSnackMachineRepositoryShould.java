package cabanas.garcia.ismael.dddinpractice.snackmachine.infrastructure.repository;

import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;
import cabanas.garcia.ismael.dddinpractice.snackmachine.infrastructure.framework.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money.DOLLAR;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("dev")
public class PostgresSnackMachineRepositoryShould {

    private static final short FIRST_POSITION = 1;
    private static final short SECOND_POSITION = 2;
    private static final short THIRD_POSITION = 3;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SnackMachineRepository snackMachineRepository;

    @Autowired
    private SnackMachineRowMapper snackMachineRowMapper;

    @Transactional
    @Test public void
    save_snack_machine() {
        Optional<SnackMachine> snackMachine = snackMachineRepository.getById("1");

        snackMachine.ifPresent(sm -> {
            sm.insertMoney(DOLLAR);
            sm.buySnack(FIRST_POSITION);
            snackMachineRepository.save(sm);
        });

        SnackMachine snackMachineSaved = getSnackMachineById(snackMachine.get().id().getValue());
        assertThat(snackMachineSaved.moneyInside()).isEqualTo(DOLLAR);
        assertThat(snackMachineSaved.snacksOfSlot(FIRST_POSITION)).isEqualTo(9);
        assertThat(snackMachineSaved.snacksOfSlot(SECOND_POSITION)).isEqualTo(10);
        assertThat(snackMachineSaved.snacksOfSlot(THIRD_POSITION)).isEqualTo(10);
    }

    private SnackMachine getSnackMachineById(String id) {
        return jdbcTemplate.queryForObject("SELECT * FROM SNACK_MACHINE INNER JOIN SLOT ON SM_ID=SL_SNACK_MACHINE_ID WHERE SM_ID = ? ",
                snackMachineRowMapper, id);
    }

}
