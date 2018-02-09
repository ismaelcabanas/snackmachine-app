package cabanas.garcia.ismael.dddinpractice.management.infrastructure.respository;

import cabanas.garcia.ismael.dddinpractice.IntegrationTest;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOffice;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeId;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;
import cabanas.garcia.ismael.dddinpractice.management.infrastructure.repository.HeadOfficeRowMapper;
import cabanas.garcia.ismael.dddinpractice.Application;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Category(IntegrationTest.class)
@ActiveProfiles("integration-test")
public class PostgresHeadOfficeRepositoryShould {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HeadOfficeRepository headOfficeRepository;

    @Autowired
    private HeadOfficeRowMapper headOfficeRowMapper;

    @Transactional @Test public void
    save_head_office() {
        Optional<HeadOffice> headOffice = headOfficeRepository.findById(new HeadOfficeId("1"));

        headOffice.ifPresent(ho -> {
            ho.changeBalance(2.00);
            headOfficeRepository.save(ho);
        });

        HeadOffice headOfficeSaved = getHeadOfficeById(headOffice.get().id().getValue());
        assertThat(headOfficeSaved.balance()).isEqualTo(2.00);
    }

    private HeadOffice getHeadOfficeById(String id) {
        return jdbcTemplate.queryForObject("SELECT * FROM HEAD_OFFICE WHERE HO_ID = ? ",
                headOfficeRowMapper, id);
    }
}
