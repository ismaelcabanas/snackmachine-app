package cabanas.garcia.ismael.dddinpractice.snackmachine.infrastructure.framework.configuration;

import cabanas.garcia.ismael.dddinpractice.snackmachine.application.BuySnackService;
import cabanas.garcia.ismael.dddinpractice.snackmachine.application.CheckStatusOfSnackMachineService;
import cabanas.garcia.ismael.dddinpractice.snackmachine.application.InsertMoneyService;
import cabanas.garcia.ismael.dddinpractice.snackmachine.application.ReturnMoneyService;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.service.TransactionService;
import cabanas.garcia.ismael.dddinpractice.snackmachine.infrastructure.service.SpringTransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class ServiceConfiguration {

    @Bean
    public TransactionService transactionService(TransactionTemplate transactionTemplate) {
        return new SpringTransactionService(transactionTemplate);
    }

    @Bean
    public BuySnackService buySnackService(SnackMachineRepository snackMachineRepository,
                                           TransactionService transactionService) {
        return new BuySnackService(snackMachineRepository, transactionService);
    }

    @Bean
    public InsertMoneyService insertMoneyService(SnackMachineRepository snackMachineRepository) {
        return new InsertMoneyService(snackMachineRepository);
    }

    @Bean
    public ReturnMoneyService returnMoneyService(SnackMachineRepository snackMachineRepository) {
        return new ReturnMoneyService(snackMachineRepository);
    }

    @Bean
    public CheckStatusOfSnackMachineService checkStatusOfSnackMachineService(SnackMachineRepository snackMachineRepository) {
        return new CheckStatusOfSnackMachineService(snackMachineRepository);
    }
}
