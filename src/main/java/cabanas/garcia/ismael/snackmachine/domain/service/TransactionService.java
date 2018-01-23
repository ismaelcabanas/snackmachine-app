package cabanas.garcia.ismael.snackmachine.domain.service;

import java.util.function.Supplier;

public interface TransactionService {
    <T> T doInTransaction(Supplier<T> supplier);
}
