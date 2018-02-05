package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository;

import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class SnackMachineHappyRepositoryStub implements SnackMachineRepository {
    private final SnackMachine snackMachine;
    private final SnackMachineRepository snackMachineRepositoryMock;

    public SnackMachineHappyRepositoryStub(SnackMachine snackMachine, SnackMachineRepository snackMachineRepositoryMock) {
        this.snackMachine = snackMachine;
        this.snackMachineRepositoryMock = snackMachineRepositoryMock;
    }

    @Override
    public void save(SnackMachine snackMachine) {
        snackMachineRepositoryMock.save(snackMachine);
    }

    @Override
    public Optional<SnackMachine> getById(String snackMachineId) {
        return Optional.of(snackMachine);
    }

    public void verifySaveSnackMachineWithNoMoneyInside() {
        ArgumentCaptor<SnackMachine> snackMachineArgCaptor = ArgumentCaptor.forClass(SnackMachine.class);
        verify(snackMachineRepositoryMock, times(1)).save(snackMachineArgCaptor.capture());
        assertThat(snackMachineArgCaptor.getValue().moneyInside()).isEqualTo(Money.none());
    }
}
