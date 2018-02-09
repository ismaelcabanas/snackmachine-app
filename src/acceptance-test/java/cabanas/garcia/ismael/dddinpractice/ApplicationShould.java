package cabanas.garcia.ismael.dddinpractice;

import cabanas.garcia.ismael.dddinpractice.AcceptanceTest;
import cabanas.garcia.ismael.dddinpractice.Application;
import cabanas.garcia.ismael.dddinpractice.atm.application.CheckStatusATMMachineService;
import cabanas.garcia.ismael.dddinpractice.atm.application.WithdrawMoneyService;
import cabanas.garcia.ismael.dddinpractice.atm.domain.model.AtmDto;
import cabanas.garcia.ismael.dddinpractice.atm.domain.model.AtmId;
import cabanas.garcia.ismael.dddinpractice.management.application.CheckStatusHeadOfficeService;
import cabanas.garcia.ismael.dddinpractice.management.application.LoadCashService;
import cabanas.garcia.ismael.dddinpractice.management.application.UnloadCashService;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeDto;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeId;
import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import cabanas.garcia.ismael.dddinpractice.snackmachine.application.BuySnackService;
import cabanas.garcia.ismael.dddinpractice.snackmachine.application.CheckStatusOfSnackMachineService;
import cabanas.garcia.ismael.dddinpractice.snackmachine.application.InsertMoneyService;
import cabanas.garcia.ismael.dddinpractice.snackmachine.application.ReturnMoneyService;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineDto;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineId;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Category(AcceptanceTest.class)
@ActiveProfiles("dev")
public class ApplicationShould {

    private static final short FIRST_POSITION = 1;

    @Autowired private InsertMoneyService insertMoneyService;

    @Autowired private ReturnMoneyService returnMoneyService;

    @Autowired private BuySnackService buySnackService;

    @Autowired private WithdrawMoneyService withdrawMoneyService;

    @Autowired private LoadCashService loadCashService;

    @Autowired private UnloadCashService unloadCashService;

    @Autowired private CheckStatusOfSnackMachineService checkStatusOfSnackMachineService;

    @Autowired private CheckStatusHeadOfficeService checkStatusHeadOfficeService;

    @Autowired private CheckStatusATMMachineService checkStatusATMMachineService;

    @Transactional
    @Test public void
    buy_snack() {
        SnackMachineId snackMachineId = new SnackMachineId("1");

        insertMoneyService.insertMoney(snackMachineId, Money.FIVE_DOLLAR);
        returnMoneyService.returnMoney(snackMachineId);

        insertMoneyService.insertMoney(snackMachineId, Money.DOLLAR);
        insertMoneyService.insertMoney(snackMachineId, Money.DOLLAR);
        buySnackService.buySnack(snackMachineId, FIRST_POSITION);

        assertThat(checkStatusOfSnackMachineService.checkStatus(snackMachineId))
                .isEqualTo(new SnackMachineDto(1.0, 0.0));
    }

    @Transactional
    @Test public void
    withdraw_money() {
        SnackMachineId snackMachineId = new SnackMachineId("1");
        HeadOfficeId headOfficeId = new HeadOfficeId("1");
        AtmId atmId = new AtmId("1");

        insertMoneyService.insertMoney(snackMachineId, Money.DOLLAR);
        buySnackService.buySnack(snackMachineId, FIRST_POSITION);
        insertMoneyService.insertMoney(snackMachineId, Money.DOLLAR);
        buySnackService.buySnack(snackMachineId, FIRST_POSITION);
        insertMoneyService.insertMoney(snackMachineId, Money.DOLLAR);
        buySnackService.buySnack(snackMachineId, FIRST_POSITION);
        insertMoneyService.insertMoney(snackMachineId, Money.DOLLAR);
        buySnackService.buySnack(snackMachineId, FIRST_POSITION);
        insertMoneyService.insertMoney(snackMachineId, Money.DOLLAR);
        buySnackService.buySnack(snackMachineId, FIRST_POSITION);

        unloadCashService.unloadCash(headOfficeId, snackMachineId);
        loadCashService.loadCash(headOfficeId, atmId);

        withdrawMoneyService.withdraw(atmId, 1.00);

        assertThat(checkStatusOfSnackMachineService.checkStatus(snackMachineId))
                .isEqualTo(new SnackMachineDto(0.0, 0.0));
        assertThat(checkStatusHeadOfficeService.checkStatus(headOfficeId))
                .isEqualTo(new HeadOfficeDto(0.0, 1.01));
        assertThat(checkStatusATMMachineService.checkStatus(atmId))
                .isEqualTo(new AtmDto(4.0));
    }
}
