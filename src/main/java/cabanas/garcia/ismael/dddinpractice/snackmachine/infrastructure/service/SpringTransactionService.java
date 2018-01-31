package cabanas.garcia.ismael.dddinpractice.snackmachine.infrastructure.service;

import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.service.TransactionService;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Supplier;

public class SpringTransactionService implements TransactionService {

    private final TransactionTemplate transactionTemplate;

    public SpringTransactionService(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public <T> T doInTransaction(Supplier<T> supplier) {
        return transactionTemplate.execute(status -> supplier.get());
    }
}
