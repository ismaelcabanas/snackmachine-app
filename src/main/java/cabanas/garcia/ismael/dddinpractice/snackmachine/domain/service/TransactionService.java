package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.service;

import java.util.function.Supplier;

public interface TransactionService {
    <T> T doInTransaction(Supplier<T> supplier);
}
