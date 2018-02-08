package cabanas.garcia.ismael.dddinpractice.snackmachine.application;

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
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineDto;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineId;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("dev")
public class AcceptanceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AcceptanceTest.class.getName());

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
        HeadOfficeId headOfficeId = new HeadOfficeId("1");

        insertMoneyService.insertMoney(snackMachineId, Money.FIVE_DOLLAR);
        LOGGER.info("SnackMachine state {}", checkStatusOfSnackMachineService.checkStatus(snackMachineId));
        returnMoneyService.returnMoney(snackMachineId);
        LOGGER.info("SnackMachine state {}", checkStatusOfSnackMachineService.checkStatus(snackMachineId));

        insertMoneyService.insertMoney(snackMachineId, Money.DOLLAR);
        LOGGER.info("SnackMachine state {}", checkStatusOfSnackMachineService.checkStatus(snackMachineId));
        insertMoneyService.insertMoney(snackMachineId, Money.DOLLAR);
        LOGGER.info("SnackMachine state {}", checkStatusOfSnackMachineService.checkStatus(snackMachineId));
        buySnackService.buySnack(snackMachineId, FIRST_POSITION);
        LOGGER.info("SnackMachine state {}", checkStatusOfSnackMachineService.checkStatus(snackMachineId));

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
        LOGGER.info("SnackMachine state {}", checkStatusOfSnackMachineService.checkStatus(snackMachineId));

        unloadCashService.unloadCash(headOfficeId, snackMachineId);
        LOGGER.info("SnackMachine state {}", checkStatusOfSnackMachineService.checkStatus(snackMachineId));
        LOGGER.info("Head Office state {}", checkStatusHeadOfficeService.checkStatus(headOfficeId));
        loadCashService.loadCash(headOfficeId, atmId);
        LOGGER.info("Head Office state {}", checkStatusHeadOfficeService.checkStatus(headOfficeId));

        withdrawMoneyService.withdraw(atmId, 1.00);

        assertThat(checkStatusOfSnackMachineService.checkStatus(snackMachineId))
                .isEqualTo(new SnackMachineDto(0.0, 0.0));
        assertThat(checkStatusHeadOfficeService.checkStatus(headOfficeId))
                .isEqualTo(new HeadOfficeDto(0.0, 1.01));
        assertThat(checkStatusATMMachineService.checkStatus(atmId))
                .isEqualTo(new AtmDto(4.0));
    }
}
