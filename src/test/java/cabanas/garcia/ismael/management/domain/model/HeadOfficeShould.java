package cabanas.garcia.ismael.management.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class HeadOfficeShould {

    @Test public void
    change_balance() {
        HeadOffice headOffice = new HeadOffice();
        double amount = 2.00;

        headOffice.changeBalance(amount);

        Assertions.assertThat(headOffice.balance()).isEqualTo(amount);
    }
}
