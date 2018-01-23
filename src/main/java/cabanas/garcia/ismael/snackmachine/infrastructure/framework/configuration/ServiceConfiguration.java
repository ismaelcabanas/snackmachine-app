package cabanas.garcia.ismael.snackmachine.infrastructure.framework.configuration;

import cabanas.garcia.ismael.snackmachine.application.BuySnackService;
import cabanas.garcia.ismael.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.snackmachine.domain.repository.SnackMachineRepository;
import cabanas.garcia.ismael.snackmachine.domain.service.TransactionService;
import cabanas.garcia.ismael.snackmachine.infrastructure.service.SpringTransactionService;
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
    public BuySnackService buySnackService(SnackMachine snackMachine, SnackMachineRepository snackMachineRepository,
                                           TransactionService transactionService) {
        return new BuySnackService(snackMachine, snackMachineRepository, transactionService);
    }
}
